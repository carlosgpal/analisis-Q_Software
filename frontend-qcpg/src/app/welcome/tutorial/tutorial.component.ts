import { Component } from '@angular/core';
import { YouTubePlayerModule } from "@angular/youtube-player";
import { SelectButtonsComponent } from '../select-buttons/select-buttons.component';

@Component({
  selector: 'app-tutorial',
  standalone: true,
  imports: [YouTubePlayerModule, SelectButtonsComponent],
  templateUrl: './tutorial.component.html',
  styleUrl: './tutorial.component.css'
})
export class TutorialComponent {

}
