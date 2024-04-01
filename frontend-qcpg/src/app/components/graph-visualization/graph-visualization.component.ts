import { Component, OnInit, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { ApiCallsService } from '@app/shared/services/apicalls.service';
import * as d3 from 'd3';
import { CommonModule } from '@angular/common';
import { Graph, Node, Link } from '@app/models/graph.models';
import { GraphControlsComponent } from '../graph-controls/graph-controls.component';
import { GraphCommunicationService } from '@app/shared/services/communication.service';

@Component({
  selector: 'app-graph-visualization',
  standalone: true,
  imports: [CommonModule, GraphControlsComponent],
  templateUrl: './graph-visualization.component.html',
  styleUrls: ['./graph-visualization.component.css']
})
export class GraphVisualizationComponent implements OnInit, AfterViewInit {
  @ViewChild('graphContainer') graphContainer!: ElementRef<HTMLElement>;
  private width: number | undefined;
  private height: number | undefined;
  private graphDataCache: { [key: string]: Graph } = {};
  private svg!: d3.Selection<SVGSVGElement, unknown, null, undefined>;
  private zoom!: d3.ZoomBehavior<Element, unknown>;

  constructor(private graphCommService: GraphCommunicationService,
    private apiCalls: ApiCallsService) { }

  ngAfterViewInit(): void {
    this.initializeSVG();
    this.loadGraph('entireGraph');
  }

  ngOnInit(): void {
    this.initializeZoom();
    this.graphCommService.graphType$.subscribe({
      next: (graphType) => {
        if (graphType === 'entireGraph' || graphType === 'ast' || graphType === 'cfg' || graphType === 'pdg') {
          this.loadGraph(graphType);
        } else {
          console.error('Tipo de grafo no válido:', graphType);
        }
      },
      error: err => console.error(err)
    });
  }

  initializeZoom(): void {
    this.zoom = d3.zoom<Element, unknown>()
      .scaleExtent([0.1, 10])
      .on("zoom", (event) => this.zoomed(event));
  }

  initializeSVG(): void {
    this.width = this.graphContainer.nativeElement.clientWidth;
    this.height = this.graphContainer.nativeElement.clientHeight;
    this.svg = d3.select(this.graphContainer.nativeElement)
      .append('svg')
      .attr('width', this.width)
      .attr('height', this.height)
      .call(this.zoom as any)
      .on("dblclick.zoom", null);

    this.svg.append('g')
      .classed('zoom-group', true);
  }

  attachZoomBehavior(): void {
    d3.select(this.graphContainer.nativeElement).select('svg')
      .call(this.zoom as any);
  }

  zoomed(event: d3.D3ZoomEvent<SVGSVGElement, unknown>): void {
    this.svg.select('g').attr('transform', event.transform.toString());
  }

  loadGraph(graphType: 'entireGraph' | 'ast' | 'cfg' | 'pdg'): void {
    if (this.graphDataCache[graphType]) {
      this.createGraph(this.graphDataCache[graphType]);
    } else {
      this.apiCalls.getGraph(graphType).subscribe({
        next: graphData => {
          this.graphDataCache[graphType] = graphData;
          this.createGraph(graphData);
        },
        error: err => console.error(err)
      });
    }
  }

  updateGraph(graphData: Graph): void {
    this.createGraph(graphData);
  }

  createGraph(graphData: Graph): void {
    const zoomGroup = this.svg.select('g.zoom-group');
    zoomGroup.selectAll('*').remove();
    const element = this.graphContainer.nativeElement;
    this.width = element.clientWidth;
    this.height = element.clientHeight;
    const nodeColor = this.getNodeColorBasedOnGraphType(graphData.type);

    const simulation = d3.forceSimulation<Node, Link>(graphData.nodes)
      .force("link", d3.forceLink<Node, Link>(graphData.edges).id(d => d.id).distance(100))
      .force("charge", d3.forceManyBody().strength(-200))
      .force("center", d3.forceCenter(this.width / 2, this.height / 2))
      .force("collision", d3.forceCollide().radius(40).strength(1));

    const linkPathId = (d: { source: string | Node; target: string | Node; }) => {
      const sourceId = typeof d.source === 'string' ? d.source : d.source.id;
      const targetId = typeof d.target === 'string' ? d.target : d.target.id;
      return `path-${sourceId}-${targetId}`;
    };

    const edgeMultiplicity: { [key: string]: { count: number, index: number } } = {};

    graphData.edges.forEach(link => {
      const sourceId = typeof link.source === 'string' ? link.source : link.source.id;
      const targetId = typeof link.target === 'string' ? link.target : link.target.id;
      const edgeKey = sourceId < targetId ? `${sourceId}:${targetId}` : `${targetId}:${sourceId}`;
      if (!edgeMultiplicity[edgeKey]) {
        edgeMultiplicity[edgeKey] = { count: 0, index: 0 };
      }
      link.index = edgeMultiplicity[edgeKey].count++;
    });

    const calculateLinkArcPath = (d: Link) => {
      const source: Node = typeof d.source === 'string' ? { id: d.source, name: '', labels: [], file: '' } : d.source;
      const target: Node = typeof d.target === 'string' ? { id: d.target, name: '', labels: [], file: '' } : d.target;
      const sourceId = source.id;
      const targetId = target.id;
      const edgeKey = sourceId < targetId ? `${sourceId}:${targetId}` : `${targetId}:${sourceId}`;
      const curvature = 1;
      const arcIndex = (d.index ?? 0) - edgeMultiplicity[edgeKey].count / 2 + 0.5;
      const arcWidth = arcIndex * 80;

      if (!source || !target) {
        throw new Error('Source or target node not found for link');
      }

      const dx = (target.x ?? 0) - (source.x ?? 0);
      const dy = (target.y ?? 0) - (source.y ?? 0);
      const dr = Math.sqrt(dx * dx + dy * dy) * curvature + arcWidth;

      if (edgeMultiplicity[edgeKey].count === 1) {
        return `M${source.x},${source.y}L${target.x},${target.y}`;
      }

      return `M${source.x},${source.y}A${dr},${dr} 0 0,1 ${target.x},${target.y}`;
    };

    const link = zoomGroup.append("g")
      .attr("class", "links")
      .selectAll("path")
      .data(graphData.edges)
      .enter().append("path")
      .attr("id", linkPathId)
      .attr("d", calculateLinkArcPath)
      .attr("stroke-width", 2)
      .attr("stroke", "#999")
      .attr("stroke-opacity", 0.6)
      .attr("fill", "none")
      .on("mouseover", function (event, d) {
        d3.select(this)
          .transition()
          .duration(200)
          .style("stroke-opacity", 1)
          .style("stroke-width", 4);
        d3.selectAll('circle')
          .filter((node: any) => {
            const sourceNode = d.source as Node;
            const targetNode = d.target as Node;
            return node.id === sourceNode.id || node.id === targetNode.id;
          })
          .transition()
          .duration(200)
          .attr("r", 30)
          .attr('stroke', 'black')
          .attr('stroke-width', 3);
      })
      .on("mouseout", function () {
        d3.select(this)
          .transition()
          .duration(200)
          .style("stroke-opacity", 0.6)
          .style("stroke-width", 2);
        d3.selectAll('circle')
          .transition()
          .duration(200)
          .attr("r", 25)
          .attr('stroke', 'none');
      });

    const node = zoomGroup.append("g")
      .attr("class", "nodes")
      .selectAll("circle")
      .data(graphData.nodes)
      .enter().append("circle")
      .attr("r", 25)
      .attr("fill", nodeColor)
      .call(d3.drag<SVGCircleElement, Node>()
        .on("start", dragstarted)
        .on("drag", dragged)
        .on("end", dragended))
      .on("mouseover", function (event, d) {
        d3.selectAll(".link").transition().duration(200)
          .style("stroke-opacity", (o: any) => (o.source === d || o.target === d ? 1 : 0.1))
          .style("stroke-width", (o: any) => (o.source === d || o.target === d ? 3 : 1));

        d3.select(this).transition().duration(200)
          .attr("r", 30);
      })
      .on("mouseout", function () {
        d3.selectAll(".link").transition().duration(200)
          .style("stroke-opacity", 0.6)
          .style("stroke-width", 2);

        d3.select(this).transition().duration(200)
          .attr("r", 25);
      });

    const linkText = zoomGroup.append("g")
      .selectAll("text")
      .data(graphData.edges)
      .enter().append("text")
      .text(d => d.type)
      .each(function (d) {
        const textElement = d3.select(this);
        const maxLength = 80;
        let textLength = textElement.node()?.getComputedTextLength();
        let text = d.type;
        while (textLength && textLength > maxLength && text.length > 0) {
          text = text.slice(0, -1);
          textElement.text(text + '...');
          textLength = textElement.node()?.getComputedTextLength();
        }
      })
      .style("text-anchor", "middle")
      .attr("x", d => (((d.source as Node).x! + (d.target as Node).x!) / 2))
      .attr("y", d => (((d.source as Node).y! + (d.target as Node).y!) / 2))
      .style("text-anchor", "middle")
      .style("overflow", "hidden")
      .style("text-overflow", "ellipsis")
      .style("white-space", "nowrap")
      .style("font-size", "10px")
      .style("dominant-baseline", "central")
      .style("alignment-baseline", "middle")
      .attr("fill", "black")
      .on("mouseover", function (event, d) {
        const sourceNode = d.source as Node;
        const targetNode = d.target as Node;
        d3.select(this)
          .transition()
          .duration(200)
          .style("font-weight", "bold");
        d3.select(`#link-path-${sourceNode.id}-${targetNode.id}`)
          .transition()
          .duration(200)
          .style("stroke-opacity", 1)
          .style("stroke-width", 4);
        d3.selectAll<SVGCircleElement, Node>('circle')
          .filter(node => node.id === sourceNode.id || node.id === targetNode.id)
          .transition()
          .duration(200)
          .attr("r", 30)
          .attr('stroke', 'black')
          .attr('stroke-width', 3);
      })
      .on("mouseout", function (event, d) {
        const sourceNode = d.source as Node;
        const targetNode = d.target as Node;
        d3.select(this)
          .transition()
          .duration(200)
          .style("font-weight", "normal");
        d3.select(`#link-path-${sourceNode.id}-${targetNode.id}`)
          .transition()
          .duration(200)
          .style("stroke-opacity", 0.6)
          .style("stroke-width", 2);
        d3.selectAll<SVGCircleElement, Node>('circle')
          .filter(node => node.id === sourceNode.id || node.id === targetNode.id)
          .transition()
          .duration(200)
          .attr("r", 25)
          .attr('stroke', 'none');
      });

    const labels = zoomGroup.append("g")
      .selectAll("text")
      .data(graphData.nodes)
      .enter()
      .append("text")
      .text(d => d.name)
      .each(function (d) {
        const textElement = d3.select(this);
        const maxLength = 72;
        let textLength = textElement.node()?.getComputedTextLength();
        let text = d.name;
        while (textLength && textLength > maxLength && text.length > 0) {
          text = text.slice(0, -1);
          textElement.text(text + '...');
          textLength = textElement.node()?.getComputedTextLength();
        }
      })
      .attr("x", d => d.x ?? 0)
      .attr("y", d => d.y ?? 0)
      .style("text-anchor", "middle")
      .style("overflow", "hidden")
      .style("text-overflow", "ellipsis")
      .style("white-space", "nowrap")
      .style("font-size", "10px")
      .style("font-weight", "bold")
      .style("pointer-events", "none")
      .style("dominant-baseline", "central")
      .style("alignment-baseline", "middle")
      .attr("fill", "white");

    simulation.on("tick", () => {

      link.attr("d", (d) => calculateLinkArcPath(d))
        .attr("x1", (d: any) => d.source.x)
        .attr("y1", (d: any) => d.source.y)
        .attr("x2", (d: any) => d.target.x)
        .attr("y2", (d: any) => d.target.y);

      node.attr("cx", (d: any) => d.x)
        .attr("cy", (d: any) => d.y);

      linkText
        .attr("x", function (d) {
          return midpoint(calculateLinkArcPath(d)).x;
        })
        .attr("y", function (d) {
          return midpoint(calculateLinkArcPath(d)).y;
        });

      labels.attr("x", (d: any) => d.x)
        .attr("y", (d: any) => d.y);
    });

    function midpoint(path: string) {
      const pathNode = document.createElementNS('http://www.w3.org/2000/svg', 'path');
      pathNode.setAttribute('d', path);

      const point = pathNode.getPointAtLength(pathNode.getTotalLength() / 2);
      return { x: point.x, y: point.y };
    }

    function dragstarted(event: any, d: any) {
      if (!event.active) simulation.alphaTarget(0.3).restart();
      d.fx = d.x;
      d.fy = d.y;
    }

    function dragged(event: any, d: any) {
      d.fx = event.x;
      d.fy = event.y;
    }

    function dragended(event: any, d: any) {
      if (!event.active) simulation.alphaTarget(0);
      d.fx = null;
      d.fy = null;
    }

    node.call(
      d3.drag<SVGCircleElement, Node>()
        .on("start", dragstarted)
        .on("drag", dragged)
        .on("end", dragended)
    );

    node.on("dblclick", (event, d) => {
      this.showPopup('node', `<strong>Nombre:</strong> ${d.name}<br><strong>Archivo:</strong> ${d.file}<br><strong>Etiquetas:</strong> ${d.labels.join(', ')}`, event.pageX, event.pageY);
    });

    link.on("dblclick", (event, d) => {
      this.showPopup('link', `<strong>Tipo:</strong> ${d.type}<br><strong>Fuente:</strong> ${(d.source as Node).name}<br><strong>Destino:</strong> ${(d.target as Node).name}`, event.pageX, event.pageY);
    });
  }

  showPopup(type: string, content: string, x: number, y: number): void {
    const popup = document.getElementById(`${type}-popup`);
    const popupContent = document.getElementById(`${type}-popup-content`);
    if (popup && popupContent) {
      popupContent.innerHTML = content;
      popup.style.left = '-9999px';
      popup.style.top = '-9999px';
      popup.style.display = 'block';

      setTimeout(() => {
        const popupWidth = popup.offsetWidth;
        const popupHeight = popup.offsetHeight;

        const viewportWidth = window.innerWidth;
        const viewportHeight = window.innerHeight;

        let calculatedX = x;
        let calculatedY = y;

        if (x + popupWidth > viewportWidth) {
          calculatedX = viewportWidth - popupWidth;
        }

        if (y + popupHeight > viewportHeight) {
          calculatedY = viewportHeight - popupHeight;
        }

        popup.style.left = `${Math.max(calculatedX, 0)}px`;
        popup.style.top = `${Math.max(calculatedY, 0)}px`;
      }, 0);
    }
  }

  hidePopup(type: string): void {
    const popup = document.getElementById(`${type}-popup`);
    if (popup) {
      popup.style.display = 'none';
    }
  }

  getNodeColorBasedOnGraphType(graphType: string): string {
    const graphTypeToColorMap: { [type: string]: string } = {
      'entireGraph': 'darkgreen',
      'ast': 'blue',
      'cfg': 'purple',
      'pdg': 'red'
    };
    return graphTypeToColorMap[graphType] || 'black';
  }
}