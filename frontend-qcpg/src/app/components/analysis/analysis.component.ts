import { Component, OnInit, ViewChild } from '@angular/core';
import { MetricCardComponent } from '@app/modules/metric-card/metric-card.component';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';
import { GraphControlsComponent } from '../graph-controls/graph-controls.component';
import { ApiCallsService } from '@app/shared/services/apicalls.service';
import { CommonModule } from '@angular/common';
import { File } from '@app/models/graph.models';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { PatternCardComponent } from '@app/modules/pattern-card/pattern-card.component';
import { HighlightJsDirective } from 'ngx-highlight-js';

@Component({
  selector: 'app-analysis',
  standalone: true,
  imports: [MetricCardComponent, GraphVisualizationComponent, GraphControlsComponent, CommonModule, MatSidenavModule, MatIconModule, PatternCardComponent, HighlightJsDirective],
  templateUrl: './analysis.component.html',
  styleUrls: ['./analysis.component.css']
})
export class AnalysisComponent implements OnInit {
  @ViewChild('sidenav', { static: true }) sidenav!: MatSidenav;

  numQubits!: string;
  numClassicBits!: string;
  numGates!: string;
  numMeasures!: string;
  numPatterns!: string;
  patterns: { title: string, route: string }[] = [];

  sidebarExpanded: boolean = false;
  files: File[] = [];
  isSidenavOpen: boolean = false;

  constructor(private apiCallsService: ApiCallsService) { }

  ngOnInit() {
    this.loadQuantumMetrics();
    this.loadFiles();
  }

  loadQuantumMetrics() {
    this.apiCallsService.getNumQubits().subscribe({
      next: (data) => {
        this.numQubits = data.toString();
      },
      error: (error) => console.error('Error fetching numQubits:', error)
    });

    this.apiCallsService.getNumClassicBits().subscribe({
      next: (data) => {
        this.numClassicBits = data.toString();
      },
      error: (error) => console.error('Error fetching numClassicBits:', error)
    });

    this.apiCallsService.getNumGates().subscribe({
      next: (data) => {
        this.numGates = data.toString();
      },
      error: (error) => console.error('Error fetching numGates:', error)
    });

    this.apiCallsService.getNumMeasures().subscribe({
      next: (data) => {
        this.numMeasures = data.toString();
      },
      error: (error) => console.error('Error fetching numMeasures:', error)
    });

    this.apiCallsService.getNumPatterns().subscribe({
      next: (data: Object) => {
        this.patterns = Object.entries(data)
          .filter(([_, count]) => count > 0)
          .map(([title, count]) => ({
            title: `${title} (${count})`,
            route: `/pattern/${title.toLowerCase()}`
          }));
        this.numPatterns = Object.values(data).reduce((total, current) => total + current, 0).toString();
      },
      error: (error) => console.error('Error fetching pattern data:', error)
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
  }
}
