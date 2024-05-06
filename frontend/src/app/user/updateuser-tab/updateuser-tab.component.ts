import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { Kategorija } from '../../models/kategorija';
import { User } from '../../models/user';
import { ProgramService } from '../../services/program.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-updateuser-tab',
  standalone: true,
  imports: [MatDialogModule, MatFormField, MatSelectModule, FormsModule, NgFor],
  templateUrl: './updateuser-tab.component.html',
  styleUrl: './updateuser-tab.component.css'
})
export class UpdateuserTabComponent {
  
  avatarUrls : string[] = ['assets/avatar_1.jfif', 'assets/avatar_2.png', 'assets/avatar_3.jpg', ''];
  categoriesList: Kategorija[] = [];
  selectedValue : string | null = null;
  user! : User;

  constructor(private userService : UserService, private programService : ProgramService) {
    if (userService.activeUser !== null){
      this.user = userService.activeUser;
    }
    this.programService.getCategories().subscribe(response => {
      this.categoriesList = response;
    });
  }


  onUpdateUser(updateUserForm: NgForm) {
    // Zbog two-way binding-a, ne moram u varijablu this.user da sacuvam nove vrijednosti iz forme
    if (updateUserForm.value.pretplata == -1)
      this.user.pretplata = null;
    else
      this.user.pretplata = this.categoriesList[updateUserForm.value.pretplata-1];

    this.userService.updateUser(this.user).subscribe(response => {
      console.log(response);
      alert("Uspješno ste promijenili svoje podatke!");
    });
}

}
