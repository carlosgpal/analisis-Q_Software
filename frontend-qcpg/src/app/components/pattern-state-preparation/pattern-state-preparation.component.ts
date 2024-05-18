import { CommonModule } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import { ApiCallsService } from '@app/shared/services/apicalls.service';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';
import { MatIconModule } from '@angular/material/icon';
import { File } from '@app/models/graph.models';

@Component({
  selector: 'app-pattern-state-preparation',
  standalone: true,
  imports: [CommonModule, MatSidenav, GraphVisualizationComponent, CommonModule, MatSidenavModule, MatIconModule],
  templateUrl: './pattern-state-preparation.component.html',
  styleUrl: './pattern-state-preparation.component.css'
})
export class PatternStatePreparationComponent {
  @ViewChild('sidenav', { static: true }) sidenav!: MatSidenav;

  numClassicBits!: string;

  constructor(private apiCallsService: ApiCallsService) { }

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
  }
}
