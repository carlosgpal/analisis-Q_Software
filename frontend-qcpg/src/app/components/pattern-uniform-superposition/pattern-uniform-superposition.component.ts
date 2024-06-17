import { Component, Renderer2, ViewChild } from '@angular/core';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import { ApiCallsService } from '@app/shared/services/apicalls.service';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { File } from '@app/models/graph.models';
import { HighlightJsDirective } from 'ngx-highlight-js';

@Component({
  selector: 'app-pattern-uniform-superposition',
  standalone: true,
  imports: [CommonModule, MatSidenav, GraphVisualizationComponent, CommonModule, MatSidenavModule, MatIconModule, HighlightJsDirective],
  templateUrl: './pattern-uniform-superposition.component.html',
  styleUrl: './pattern-uniform-superposition.component.css'
})
export class PatternUniformSuperpositionComponent {
  @ViewChild('sidenav', { static: true }) sidenav!: MatSidenav;

  numClassicBits!: string;

  constructor(private apiCallsService: ApiCallsService, private renderer: Renderer2) { }

  sidebarExpanded: boolean = false;
  files: File[] = [];
  isSidenavOpen: boolean = false;

  ngOnInit() {
    this.loadQuantumMetrics();
    this.loadFiles();
  }

  loadQuantumMetrics() {
    this.apiCallsService.getNumClassicBits().subscribe({
      next: (data) => {
        this.numClassicBits = data.toString();
      },
      error: (error) => console.error('Error fetching numClñassicBits:', error)
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
