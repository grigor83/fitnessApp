import { Routes } from '@angular/router';
import { GuardService } from './services/guard.service';
import { ContainerPageComponent } from './user/container-page/container-page.component';
import { HomePageComponent } from './home/home-page/home-page.component';
import { FitnessProgramComponent } from './fitness-page/fitness-program/fitness-program.component';
import { GuardFitnesspageService } from './services/guard-fitnesspage.service';

export const routes: Routes = [
    {path : "home", component : HomePageComponent},
    {path: '', redirectTo : '/home', pathMatch : 'full'},
    {path : "fitnes-program", component : FitnessProgramComponent, canActivate : [GuardFitnesspageService]},
    {path : "user-page", component : ContainerPageComponent, canActivate : [GuardService]},
    {path: '**', component : HomePageComponent}
];
