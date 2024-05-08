import { Component } from '@angular/core';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';
import { MetricCardComponent } from '@app/modules/metric-card/metric-card.component';
import { ApiCallsService } from '@app/shared/services/apicalls.service';

@Component({
  selector: 'app-mapping-gates',
  standalone: true,
  imports: [GraphVisualizationComponent, MetricCardComponent],
  templateUrl: './mapping-gates.component.html',
  styleUrl: './mapping-gates.component.css'
})
export class MappingGatesComponent {
  numGates!: string;

  constructor(private apiCallsService: ApiCallsService) { }

  ngOnInit() {
    this.loadQuantumMetrics();
  }

  loadQuantumMetrics() {
    this.apiCallsService.getNumGates().subscribe({
      next: (data) => {
        this.numGates = data.toString();
      },
      error: (error) => console.error('Error fetching numGates:', error)
    });
  }

}
