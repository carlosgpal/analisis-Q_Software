import { Component } from '@angular/core';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';
import { MetricCardComponent } from '@app/modules/metric-card/metric-card.component';
import { ApiCallsService } from '@app/shared/services/apicalls.service';

@Component({
  selector: 'app-mapping-bits',
  standalone: true,
  imports: [GraphVisualizationComponent, MetricCardComponent],
  templateUrl: './mapping-bits.component.html',
  styleUrl: './mapping-bits.component.css'
})
export class MappingBitsComponent {
  numClassicBits!: string;

  constructor(private apiCallsService: ApiCallsService) { }

  ngOnInit() {
    this.loadQuantumMetrics();
  }

  loadQuantumMetrics() {
    this.apiCallsService.getNumClassicBits().subscribe({
      next: (data) => {
        this.numClassicBits = data.toString();
      },
      error: (error) => console.error('Error fetching numClñassicBits:', error)
    });
  }
}
