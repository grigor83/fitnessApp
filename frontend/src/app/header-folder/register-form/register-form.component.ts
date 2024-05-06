import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { Kategorija } from '../../models/kategorija';
import { ProgramService } from '../../services/program.service';
import { User } from '../../models/user';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-register-form',
  standalone: true,
  imports: [MatDialogModule, MatFormField, FormsModule, MatSelectModule, NgFor],
  templateUrl: './register-form.component.html',
  styleUrl: './register-form.component.css'
})
export class RegisterFormComponent {

  avatarUrls : string[] = ['assets/avatar_1.jfif', 'assets/avatar_2.png', 'assets/avatar_3.jpg', ''];
  categoriesList: Kategorija[] = [];
  selectedValue : string | null = null;

  constructor(private programService : ProgramService, private userService : UserService, 
    private snackBar: MatSnackBar, private dialogRef: MatDialogRef<RegisterFormComponent>) {
    this.programService.getCategories().subscribe(response => {
      this.categoriesList = response;
    });
  }


  register(registerForm: NgForm) {
    this.userService.getAllUsers().subscribe(response => {
      this.userService.users = [];
      this.userService.users = response;
      this.userService.users = this.userService.users.filter(user => user.korisnickoIme == registerForm.value.username);
      if (this.userService.users.length > 0){
        alert("Uneseno korisničko ime je zauzeto! Izaberite neko drugo!")
        return;
      }

      const newUser = new User(registerForm.value.name, registerForm.value.city, registerForm.value.username,
                          registerForm.value.password, registerForm.value.email, registerForm.value.cardnumber, registerForm.value.avatar);
      if (registerForm.value.pretplata != -1)
        newUser.pretplata = this.categoriesList[registerForm.value.pretplata-1];

      this.userService.registerUser(newUser).subscribe(response => {
        console.log(response);
        this.snackBar.open("Verifikacioni mejl je poslat na adresu vaše elektronske pošte!", undefined, { 
          duration: 2000, verticalPosition: 'top'
        }); 
        this.dialogRef.close();
      });
    })
  }

}
