import { Component } from '@angular/core';
import { SelectButtonsComponent } from '../select-buttons/select-buttons.component';

@Component({
  selector: 'app-tutorial',
  standalone: true,
  imports: [SelectButtonsComponent],
  templateUrl: './tutorial.component.html',
  styleUrl: './tutorial.component.css'
})
export class TutorialComponent {

}
