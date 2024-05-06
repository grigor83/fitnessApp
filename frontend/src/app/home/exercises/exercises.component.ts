import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-exercises',
  standalone: true,
  imports: [MatCardModule],
  templateUrl: './exercises.component.html',
  styleUrl: './exercises.component.css'
})
export class ExercisesComponent {

  @Input() title! : string;
  @Input() instructions! : string;
  @Input() level! : string;

}
