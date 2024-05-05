import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-metric-card',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './metric-card.component.html',
  styleUrls: ['./metric-card.component.css']
})
export class MetricCardComponent {
  @Input() title: string;
  @Input() value: string;
  @Input() route: string;

  constructor() {
    this.title = '';
    this.value = '';
    this.route = '';
  }
}