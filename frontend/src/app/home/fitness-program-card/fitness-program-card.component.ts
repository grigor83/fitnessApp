import { Component, Input, OnInit } from '@angular/core';
import {MatCardModule} from '@angular/material/card';


@Component({
  selector: 'app-fitness-program-card',
  standalone: true,
  imports: [MatCardModule],
  templateUrl: './fitness-program-card.component.html',
  styleUrl: './fitness-program-card.component.css'
})
export class FitnessProgramCardComponent implements OnInit {
  
  @Input() naziv = 'naziv';
  @Input() nazivSlike : string | undefined;
  @Input() cijena = 25;
  
  ngOnInit(): void {
  }
}
