import { Component } from '@angular/core';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';

@Component({
  selector: 'app-mapping-measures',
  standalone: true,
  imports: [GraphVisualizationComponent],
  templateUrl: './mapping-measures.component.html',
  styleUrl: './mapping-measures.component.css'
})
export class MappingMeasuresComponent {

}
