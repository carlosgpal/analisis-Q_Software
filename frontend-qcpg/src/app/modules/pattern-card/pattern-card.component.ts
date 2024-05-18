import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-pattern-card',
  standalone: true,
  imports: [CommonModule, MatIcon],
  templateUrl: './pattern-card.component.html',
  styleUrl: './pattern-card.component.css'
})
export class PatternCardComponent {
  @Input() title: string = '';
  @Input() value: string = '';
  @Input() patterns: { title: string, route: string }[] = [];
  @Input() showPopupButton: boolean = false;

  isOpen: boolean = false;

  constructor() { }

  togglePopup(): void {
    this.isOpen = !this.isOpen;
  }
}
