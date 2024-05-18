import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class GraphCommunicationService {
  private graphTypeSource = new Subject<string>();

  graphType$ = this.graphTypeSource.asObservable();

  changeGraphType(type: 'entireGraph' | 'ast' | 'cfg' | 'pdg' | 'mappingBits' | 'mappingGates' | 'mappingMeasures' | 'statePreparation' | 'uniformSuperposition' | 'creatingEntanglement'): void {
    this.graphTypeSource.next(type);
  }
}