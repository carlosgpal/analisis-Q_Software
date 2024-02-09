import { Component } from '@angular/core';
import { NavigationComponent } from '../navigation/navigation.component';
import { RouterOutlet } from '@angular/router';
import { FooterComponent } from '../footer/footer.component';
import { TutorialComponent } from '@app/welcome/tutorial/tutorial.component';
import { SelectButtonsComponent } from '@app/welcome/select-buttons/select-buttons.component';

@Component({
  selector: 'app-skeleton',
  standalone: true,
  imports: [NavigationComponent, FooterComponent, RouterOutlet, TutorialComponent, SelectButtonsComponent],
  templateUrl: './skeleton.component.html',
  styleUrl: './skeleton.component.css'
})
export class SkeletonComponent {

}
