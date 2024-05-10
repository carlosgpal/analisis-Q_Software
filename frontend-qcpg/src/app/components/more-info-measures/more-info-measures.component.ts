import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { ApiCallsService } from '@app/shared/services/apicalls.service';
import { Measure, GroupedMeasures, Group } from '@app/models/graph.models';

@Component({
  selector: 'app-more-info-measures',
  standalone: true,
  imports: [CommonModule, MatIcon],
  templateUrl: './more-info-measures.component.html',
  styleUrl: './more-info-measures.component.css'
})

export class MoreInfoMeasuresComponent implements OnInit {
  groupedMeasures: GroupedMeasures = {};

  constructor(private apiCallsService: ApiCallsService) { }

  ngOnInit(): void {
    this.apiCallsService.getMoreInfoQubitsMeasures().subscribe(data => {
      this.groupMeasures(data);
    });
  }

  groupMeasures(measures: Measure[]): void {
    const grouped: GroupedMeasures = {};
    measures.forEach(measure => {
      const groupKey = measure.classicBit;
      if (!grouped[groupKey]) {
        grouped[groupKey] = { measures: [], isExpanded: false };
      }
      grouped[groupKey].measures.push(measure);
    });
    this.groupedMeasures = grouped;
  }

  toggleExpand(key: string): void {
    this.groupedMeasures[key].isExpanded = !this.groupedMeasures[key].isExpanded;
  }

  objectKeys(obj: any): string[] {
    return Object.keys(obj);
  }
}