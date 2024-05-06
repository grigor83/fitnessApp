import { Injectable } from '@angular/core';
import { FitnessProgram } from '../models/fitness-program';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  private fitnesProgram! : FitnessProgram;
  private buttonVisibilitySubject = new BehaviorSubject<boolean>(true);
  buttonVisibility$ = this.buttonVisibilitySubject.asObservable();
  private buttonVisibilitySubject2 = new BehaviorSubject<boolean>(true);
  buttonVisibility2$ = this.buttonVisibilitySubject2.asObservable();

  hideBackButton(visible: boolean) {
    this.buttonVisibilitySubject.next(visible);
  }

  hideLoginButton(visible : boolean){
    this.buttonVisibilitySubject2.next(visible);
  }

  setFitnessProgram(fitnesProgram: FitnessProgram) {
    this.fitnesProgram = fitnesProgram;
  }

  getFitnessProgram(): FitnessProgram {
    return this.fitnesProgram;
  }


}
