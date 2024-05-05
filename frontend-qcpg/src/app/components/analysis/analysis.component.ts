import { Component, OnInit } from '@angular/core';
import { MetricCardComponent } from '@app/modules/metric-card/metric-card.component';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';
import { GraphControlsComponent } from '../graph-controls/graph-controls.component';
import { ApiCallsService } from '@app/shared/services/apicalls.service';

@Component({
  selector: 'app-analysis',
  standalone: true,
  imports: [MetricCardComponent, GraphVisualizationComponent, GraphControlsComponent],
  templateUrl: './analysis.component.html',
  styleUrls: ['./analysis.component.css']
})
export class AnalysisComponent implements OnInit {
  numQubits!: string;
  numClassicBits!: string;
  numGates!: string;
  numMeasures!: string;

  constructor(private apiCallsService: ApiCallsService) { }

  ngOnInit() {
    this.loadQuantumMetrics();
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

  }
}