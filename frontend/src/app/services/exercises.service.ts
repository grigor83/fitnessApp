import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ExercisesService {

  private url = "https://api.api-ninjas.com/v1/exercises";
  exercises : any | null;


  constructor(private httpClient : HttpClient) { }

  getExercises() {
    const headers = new HttpHeaders().set('X-Api-Key', 'yfs6fszCkXgnzPsLoxHWsA==s4FWOVqqOC0juD0y');
    return this.httpClient.get(this.url, {headers});
  }

}
