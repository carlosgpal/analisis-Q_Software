import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { ApiCallsService } from '@app/shared/services/apicalls.service';

@Component({
  selector: 'app-moreinfo-creating-entanglement',
  standalone: true,
  imports: [CommonModule, MatIcon],
  templateUrl: './moreinfo-creating-entanglement.component.html',
  styleUrl: './moreinfo-creating-entanglement.component.css'
})
export class MoreinfoCreatingEntanglementComponent {
  qubitsBits: any[] = [];

  constructor(private apiCallsService: ApiCallsService) { }

  ngOnInit(): void {
    this.apiCallsService.getMoreInfoCreatingEntanglement().subscribe(data => {
      this.qubitsBits = data.map(item => ({ ...item, isExpanded: false }));
    });
  }

  toggleExpand(item: any): void {
    item.isExpanded = !item.isExpanded;
  }
}
