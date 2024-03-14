import { Component, EventEmitter, Output } from '@angular/core';
import { GraphCommunicationService } from '@app/shared/services/communication.service';

@Component({
  selector: 'app-graph-controls',
  standalone: true,
  imports: [],
  templateUrl: './graph-controls.component.html',
  styleUrl: './graph-controls.component.css'
})

export class GraphControlsComponent {
  constructor(private graphCommService: GraphCommunicationService) { }

  emitGraphChange(graphType: 'entireGraph' | 'ast' | 'cfg' | 'pdg'): void {
    this.graphCommService.changeGraphType(graphType);
  }

}