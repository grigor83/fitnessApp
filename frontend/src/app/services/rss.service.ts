import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Parser from 'rss-parser';

@Injectable({
  providedIn: 'root'
})
export class RssService {
  private url = "https://feeds.feedburner.com/AceFitFacts";
  rssFeedData : any | null;

  constructor(private httpClient : HttpClient) { }

  getRssFeed() {
    //const headers = new HttpHeaders().set('Origin', '*');
    return this.httpClient.get(this.url, { responseType: 'text' });
  }

  parseRssFeed(xml: string) {
    const parser = new Parser();
    return parser.parseString(xml);
  }

}
