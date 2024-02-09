import { Component } from '@angular/core';
import { GraphComponent } from '../graph/graph.component';
import { MetricCardComponent } from '../metric-card/metric-card.component';

@Component({
  selector: 'app-analysis',
  standalone: true,
  imports: [MetricCardComponent, GraphComponent],
  templateUrl: './analysis.component.html',
  styleUrl: './analysis.component.css'
})
export class AnalysisComponent {

}
