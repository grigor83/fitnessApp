import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { SharedService } from '../../services/shared.service';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [MatDialogModule, MatFormField, FormsModule],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent {

  constructor(private userService : UserService, private sharedService : SharedService, 
    private dialogRef: MatDialogRef<LoginFormComponent>, private router : Router) {}


  login(loginForm: NgForm) {
    this.userService.getAllUsers().subscribe(response => {
      this.userService.users = response;

      if (this.userService.login(loginForm.value.username, loginForm.value.password)){
        this.sharedService.hideLoginButton(true);
        this.router.navigate(['/user-page']);
        this.dialogRef.close();
      }
    })
  }

}
