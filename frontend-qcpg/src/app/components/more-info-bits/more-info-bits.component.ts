import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { ApiCallsService } from '@app/shared/services/apicalls.service';

@Component({
  selector: 'app-more-info-bits',
  standalone: true,
  imports: [CommonModule, MatIcon],
  templateUrl: './more-info-bits.component.html',
  styleUrl: './more-info-bits.component.css'
})
export class MoreInfoBitsComponent implements OnInit {
  qubitsBits: any[] = [];

  constructor(private apiCallsService: ApiCallsService) { }

  ngOnInit(): void {
    this.apiCallsService.getMoreInfoQubitsBits().subscribe(data => {
      this.qubitsBits = data.map(item => ({ ...item, isExpanded: false }));
    });
  }

  toggleExpand(item: any): void {
    item.isExpanded = !item.isExpanded;
  }
}