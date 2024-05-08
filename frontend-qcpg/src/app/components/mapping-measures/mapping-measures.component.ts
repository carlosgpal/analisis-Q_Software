import { Component } from '@angular/core';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';
import { MetricCardComponent } from '@app/modules/metric-card/metric-card.component';
import { ApiCallsService } from '@app/shared/services/apicalls.service';

@Component({
  selector: 'app-mapping-measures',
  standalone: true,
  imports: [GraphVisualizationComponent, MetricCardComponent],
  templateUrl: './mapping-measures.component.html',
  styleUrl: './mapping-measures.component.css'
})
export class MappingMeasuresComponent {
  numMeasures!: string;

  constructor(private apiCallsService: ApiCallsService) { }

  ngOnInit() {
    this.loadQuantumMetrics();
  }

  loadQuantumMetrics() {
    this.apiCallsService.getNumMeasures().subscribe({
      next: (data) => {
        this.numMeasures = data.toString();
      },
      error: (error) => console.error('Error fetching nuMeasures:', error)
    });
  }

}
