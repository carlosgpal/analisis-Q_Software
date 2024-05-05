import { Component } from '@angular/core';
import { GraphVisualizationComponent } from '../graph-visualization/graph-visualization.component';

@Component({
  selector: 'app-mapping-gates',
  standalone: true,
  imports: [GraphVisualizationComponent],
  templateUrl: './mapping-gates.component.html',
  styleUrl: './mapping-gates.component.css'
})
export class MappingGatesComponent {

}
