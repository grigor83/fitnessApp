import { NgFor } from '@angular/common';
import { ChangeDetectorRef, Component, ElementRef, QueryList, ViewChildren } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { Category } from '../../models/category';
import { ProgramService } from '../../services/program.service';
import { User } from '../../models/user';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../../services/user.service';
import { Subscription } from '../../models/subscription';
import { SubscriptionService } from '../../services/subscription.service';

@Component({
  selector: 'app-register-form',
  standalone: true,
  imports: [MatDialogModule, MatFormField, FormsModule, MatSelectModule, NgFor],
  templateUrl: './register-form.component.html',
  styleUrl: './register-form.component.css'
})
export class RegisterFormComponent {

  avatarUrls : string[] = ['assets/avatar_1.jfif', 'assets/avatar_2.png', 'assets/avatar_3.jpg', ''];
  categoriesList: Category[] = [];
  @ViewChildren('checkbox')
  checkboxes!: QueryList<ElementRef>;
  selectedCategories: Category[] = [];

  constructor(private programService : ProgramService, private userService : UserService, private subsService : SubscriptionService,
    private snackBar: MatSnackBar, private dialogRef: MatDialogRef<RegisterFormComponent>, private cdr: ChangeDetectorRef) {
    this.programService.getCategories().subscribe(response => {
      this.categoriesList = response;
    });
  }

  ngOnInit(){
    this.cdr.detectChanges();
  }

  logCheckboxStates(newUser : User) {
    this.checkboxes.forEach((checkbox, index) => {
      if (checkbox.nativeElement.checked) {
        let cat = this.categoriesList.find(kategorija => kategorija.id == checkbox.nativeElement.value);
        if (cat != null)
          this.selectedCategories.push(cat);
      }
    });

    this.createSubscriptions(newUser);
  }

  createSubscriptions(newUser : User){
    if (this.selectedCategories.length === 0)
      return;

    this.selectedCategories.forEach(cat => {
      let sub = new Subscription();
      sub.category = cat;
      sub.user = newUser;

      this.subsService.createSubscription(sub).subscribe(response => {
        console.log(response);
      });
    });
  }


  register(registerForm: NgForm) {
    this.userService.getAllUsers().subscribe(response => {
      this.userService.users = [];
      this.userService.users = response;
      this.userService.users = this.userService.users.filter(user => user.username == registerForm.value.username);
      if (this.userService.users.length > 0){
        alert("Uneseno korisničko ime je zauzeto! Izaberite neko drugo!")
        return;
      }

      const newUser = new User(registerForm.value.name, registerForm.value.city, registerForm.value.username,
                          registerForm.value.password, registerForm.value.email, registerForm.value.cardnumber, registerForm.value.avatar);

      this.userService.registerUser(newUser).subscribe(response => {
        console.log(response);
        newUser.id = response.id;
        this.snackBar.open("Verifikacioni mejl je poslat na adresu vaše elektronske pošte!", undefined, { 
          duration: 2000, verticalPosition: 'top'
        }); 
        this.logCheckboxStates(newUser);
        this.dialogRef.close();
      });
    })
  }

}
