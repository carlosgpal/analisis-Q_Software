import { Component, Renderer2, ViewChild } from '@angular/core';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';
import { MetricCardComponent } from '@app/modules/metric-card/metric-card.component';
import { ApiCallsService } from '@app/shared/services/apicalls.service';
import { CommonModule } from '@angular/common';
import { File } from '@app/models/graph.models';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MoreInfoMeasuresComponent } from '../more-info-measures/more-info-measures.component';
import { HighlightJsDirective } from 'ngx-highlight-js';

@Component({
  selector: 'app-mapping-measures',
  standalone: true,
  imports: [MetricCardComponent, GraphVisualizationComponent, CommonModule, MatSidenavModule, MatIconModule, MoreInfoMeasuresComponent, HighlightJsDirective],
  templateUrl: './mapping-measures.component.html',
  styleUrl: './mapping-measures.component.css'
})
export class MappingMeasuresComponent {
  @ViewChild('sidenav', { static: true }) sidenav!: MatSidenav;

  numMeasures!: string;

  constructor(private apiCallsService: ApiCallsService, private renderer: Renderer2) { }

  sidebarExpanded: boolean = false;
  files: File[] = [];
  isSidenavOpen: boolean = false;

  ngOnInit() {
    this.loadQuantumMetrics();
    this.loadFiles();
  }

  loadQuantumMetrics() {
    this.apiCallsService.getNumMeasures().subscribe({
      next: (data) => {
        this.numMeasures = data.toString();
      },
      error: (error) => console.error('Error fetching nuMeasures:', error)
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
