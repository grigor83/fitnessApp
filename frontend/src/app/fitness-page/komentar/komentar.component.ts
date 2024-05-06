import { Component, Input } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-karta',
  standalone: true,
  imports: [MatCardModule, MatButton],
  templateUrl: './komentar.component.html',
  styleUrl: './komentar.component.css'
})
export class KomentarComponent {
  @Input() komentar! : string;
  @Input() datum! : string;
  @Input() ime! : string;

}
