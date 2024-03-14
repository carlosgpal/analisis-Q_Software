import { Component } from '@angular/core';
import { MetricCardComponent } from '@app/modules/metric-card/metric-card.component';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';
import { GraphControlsComponent } from '../graph-controls/graph-controls.component';

@Component({
  selector: 'app-analysis',
  standalone: true,
  imports: [MetricCardComponent, GraphVisualizationComponent, GraphControlsComponent],
  templateUrl: './analysis.component.html',
  styleUrls: ['./analysis.component.css']
})
export class AnalysisComponent { }