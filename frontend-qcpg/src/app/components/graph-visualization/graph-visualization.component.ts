import { Component, OnInit, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { ApiCallsService } from '@app/shared/services/apicalls.service';
import * as d3 from 'd3';
import { CommonModule } from '@angular/common';
import { Graph, Node, Link, GraphCache } from '@app/models/graph.models';
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

  constructor(private graphCommService: GraphCommunicationService,
    private apiCalls: ApiCallsService) { }

  ngAfterViewInit(): void {
    this.loadGraph('entireGraph');
  }

  ngOnInit(): void {
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
    d3.select(this.graphContainer.nativeElement).select('svg').remove();

    const element = this.graphContainer.nativeElement;
    this.width = element.clientWidth;
    this.height = element.clientHeight;

    const svg = d3.select(element).append('svg')
      .attr('width', this.width)
      .attr('height', this.height);

    const simulation = d3.forceSimulation<Node, Link>(graphData.nodes)
      .force("link", d3.forceLink<Node, Link>(graphData.edges).id(d => d.id))
      .force("charge", d3.forceManyBody().strength((d, i) => {
        return -30;
      }))
      .force("center", d3.forceCenter(this.width / 2, this.height / 2))
      .force("collision", d3.forceCollide().radius(40).strength(1));

    const link = svg.append("g")
      .attr("class", "links")
      .selectAll("line")
      .data(graphData.edges)
      .enter().append("line")
      .attr("stroke-width", 4)
      .style("stroke", (d: Link) => this.linkTypeToColor(d.type));

    const node = svg.append("g")
      .attr("class", "nodes")
      .selectAll("circle")
      .data(graphData.nodes)
      .enter().append("circle")
      .attr("r", 25)
      .attr("fill", "blue");

    const labels = svg.append("g")
      .attr("class", "labels")
      .selectAll("text")
      .data(graphData.nodes)
      .enter().append("text")
      .text(d => d.name)
      .style("text-anchor", "middle")
      .style("pointer-events", "none")
      .attr("fill", "white");

    node.call(d3.drag<SVGCircleElement, Node>()
      .on("start", (event, d) => {
        if (!event.active) simulation.alphaTarget(0.3).restart();
        d.fx = d.x;
        d.fy = d.y;
      })
      .on("drag", (event, d) => {
        d.fx = event.x;
        d.fy = event.y;
      }));

    const nodeRadius = 20;

    const drag = d3.drag<SVGCircleElement, Node>()
      .on("start", (event, d) => {
        if (!event.active) simulation.alphaTarget(0.3).restart();
        d.fx = d.x;
        d.fy = d.y;
      })
      .on("drag", (event, d) => {
        d.fx = Math.max(nodeRadius, Math.min(this.width ? this.width - nodeRadius : 0, event.x));
        d.fy = Math.max(nodeRadius, Math.min(this.height ?? 0 - nodeRadius, event.y));
      })
      .on("end", (event, d) => {
        if (!event.active) simulation.alphaTarget(0);
        d.fx = null;
        d.fy = null;
      });

    node.call(drag);

    simulation.on("tick", () => {
      link
        .attr("x1", d => (typeof d.source !== 'string' ? (d.source as Node).x : 0) ?? 0)
        .attr("y1", d => (typeof d.source !== 'string' ? (d.source as Node).y : 0) ?? 0)
        .attr("x2", d => (typeof d.target !== 'string' ? (d.target as Node).x : 0) ?? 0)
        .attr("y2", d => (typeof d.target !== 'string' ? (d.target as Node).y : 0) ?? 0);

      node
        .attr("cx", (d: Node) => d.x ?? 0)
        .attr("cy", (d: Node) => d.y ?? 0);

      labels
        .attr("x", (d: Node) => d.x ?? 0)
        .attr("y", (d: Node) => (d.y ?? 0));
    });

    node.on("dblclick", (event, d) => {
      this.showPopup('node', `Name: ${d.name}<br>Labels: ${d.labels.join(', ')}`, event.pageX, event.pageY);
    });

    link.on("dblclick", (event, d) => {
      this.showPopup('link', `Type: ${d.type}`, event.pageX, event.pageY);
    });
  }

  showPopup(type: string, content: string, x: number, y: number): void {
    const popup = document.getElementById(`${type}-popup`);
    const popupContent = document.getElementById(`${type}-popup-content`);
    if (popup && popupContent) {
      popupContent.innerHTML = content;
      popup.style.display = 'block';
      popup.style.left = `${x}px`;
      popup.style.top = `${y}px`;
    }
  }

  hidePopup(type: string): void {
    const popup = document.getElementById(`${type}-popup`);
    if (popup) {
      popup.style.display = 'none';
    }
  }

  linkTypeToColor(type: string): string {
    const typeToColorMap: { [type: string]: string } = {
      'TYPE1': 'red',
      'TYPE2': 'green',
    };
    return typeToColorMap[type] || 'black';
  }
}
