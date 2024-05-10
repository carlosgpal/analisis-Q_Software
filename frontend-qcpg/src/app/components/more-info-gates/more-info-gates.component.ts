import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { ApiCallsService } from '@app/shared/services/apicalls.service';

@Component({
  selector: 'app-more-info-gates',
  standalone: true,
  imports: [CommonModule, MatIcon],
  templateUrl: './more-info-gates.component.html',
  styleUrl: './more-info-gates.component.css'
})
export class MoreInfoGatesComponent implements OnInit {
  qubitsGates: any[] = [];

  constructor(private apiCallsService: ApiCallsService) { }

  ngOnInit(): void {
    this.apiCallsService.getMoreInfoQubitsGates().subscribe(data => {
      this.qubitsGates = data.map(item => ({ ...item, isExpanded: false }));
    });
  }

  toggleExpand(item: any): void {
    item.isExpanded = !item.isExpanded;
  }
}