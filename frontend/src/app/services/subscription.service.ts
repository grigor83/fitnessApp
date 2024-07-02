import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subscription } from '../models/subscription';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  private url = 'http://localhost:8080/subscriptions';

  constructor(private httpClient : HttpClient) { }

  createSubscription(subs : Subscription) {
    return this.httpClient.post<Subscription>(this.url, subs);
  }

  getSelectedSubscriptions(user : User | null){
    return this.httpClient.get<Subscription[]>(`http://localhost:8080/subscriptions/${user?.id}`);
  }

  deleteSubscription(id : number): Observable<string> {
    return this.httpClient.delete<string>(`${this.url}/${id}`);
  }
}
