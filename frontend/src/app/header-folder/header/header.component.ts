import { CommonModule, NgIf, NgStyle } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from '../../services/shared.service';
import { MatDialog } from '@angular/material/dialog';
import { LoginFormComponent } from '../login-form/login-form.component';
import { RegisterFormComponent } from '../register-form/register-form.component';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [NgStyle, NgIf, CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})


export class HeaderComponent {

  constructor(public sharedService : SharedService, public userService : UserService, private dialog : MatDialog, private router : Router){}

  homePage() {
    this.sharedService.hideBackButton(true);
    this.router.navigate(['/home']);
  }

  userPage() {
    this.router.navigate(['/user-page']);
  }

  login() {
    const dialogRef = this.dialog.open(LoginFormComponent, {
      width: '420px',
    });
  }

  logout() {
    this.userService.logout();
    this.sharedService.hideLoginButton(false);
    this.router.navigate(['/home']);
  }

  register() {
    const dialogRef = this.dialog.open(RegisterFormComponent, {
      width: '420px',
    });
  }    

}
