import { Component } from '@angular/core';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';

@Component({
  selector: 'app-mapping-bits',
  standalone: true,
  imports: [GraphVisualizationComponent],
  templateUrl: './mapping-bits.component.html',
  styleUrl: './mapping-bits.component.css'
})
export class MappingBitsComponent {

}
