import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { ExercisesService } from './exercises.service';

@Injectable({
  providedIn: 'root'
})
export class GuardFitnesspageService implements CanActivate {

  constructor( private exercisesService : ExercisesService, private router : Router) { }

  canActivate() : boolean {
    if (this.exercisesService.exercises != null)
      return true;
    else {
      this.router.navigate(['']);
      return false;
    }
  }
}
