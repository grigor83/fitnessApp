import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-rss-feed',
  standalone: true,
  imports: [MatCardModule],
  templateUrl: './rss-feed.component.html',
  styleUrl: './rss-feed.component.css'
})
export class RssFeedComponent {
  @Input() title! : string;
  @Input() content! : string;
  @Input() link! : string;

}
