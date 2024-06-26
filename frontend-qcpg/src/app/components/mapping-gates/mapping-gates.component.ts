import { Component, Renderer2, ViewChild } from '@angular/core';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';
import { MetricCardComponent } from '@app/modules/metric-card/metric-card.component';
import { ApiCallsService } from '@app/shared/services/apicalls.service';
import { File } from '@app/models/graph.models';
import { CommonModule } from '@angular/common';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MoreInfoGatesComponent } from '../more-info-gates/more-info-gates.component';
import { HighlightJsDirective } from 'ngx-highlight-js';

@Component({
  selector: 'app-mapping-gates',
  standalone: true,
  imports: [MetricCardComponent, GraphVisualizationComponent, CommonModule, MatSidenavModule, MatIconModule, MoreInfoGatesComponent, HighlightJsDirective],
  templateUrl: './mapping-gates.component.html',
  styleUrl: './mapping-gates.component.css'
})
export class MappingGatesComponent {
  @ViewChild('sidenav', { static: true }) sidenav!: MatSidenav;

  numGates!: string;

  constructor(private apiCallsService: ApiCallsService, private renderer: Renderer2) { }

  sidebarExpanded: boolean = false;
  files: File[] = [];
  isSidenavOpen: boolean = false;

  ngOnInit() {
    this.loadQuantumMetrics();
    this.loadFiles();
  }

  loadQuantumMetrics() {
    this.apiCallsService.getNumGates().subscribe({
      next: (data) => {
        this.numGates = data.toString();
      },
      error: (error) => console.error('Error fetching numGates:', error)
    });
  }

  loadFiles() {
    this.apiCallsService.getFiles().subscribe({
      next: (files: File[]) => {
        this.files = files.map(file => ({
          ...file,
          open: false
        }));
      },
      error: (error) => console.error('Error fetching files:', error)
    });
  }

  toggleFile(file: File) {
    file.open = !file.open;
  }

  toggleExpand(file: File) {
    file.open = !file.open;
  }

  toggleSidenav() {
    this.sidenav.toggle();
    this.isSidenavOpen = !this.isSidenavOpen;
    const graphVisualization = document.querySelector('app-graph-visualization');

    if (this.isSidenavOpen) {
      this.renderer.removeClass(graphVisualization, 'graph-visualization-closed');
      this.renderer.addClass(graphVisualization, 'graph-visualization-open');
    } else {
      this.renderer.removeClass(graphVisualization, 'graph-visualization-open');
      this.renderer.addClass(graphVisualization, 'graph-visualization-closed');
    }
  }
}
