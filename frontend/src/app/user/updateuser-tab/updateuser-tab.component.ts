import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { Category } from '../../models/category';
import { User } from '../../models/user';
import { ProgramService } from '../../services/program.service';
import { UserService } from '../../services/user.service';
import { SubscriptionService } from '../../services/subscription.service';
import { Subscription } from '../../models/subscription';

@Component({
  selector: 'app-updateuser-tab',
  standalone: true,
  imports: [MatDialogModule, MatFormField, MatSelectModule, FormsModule, NgFor],
  templateUrl: './updateuser-tab.component.html',
  styleUrl: './updateuser-tab.component.css'
})
export class UpdateuserTabComponent {
  
  avatarUrls : string[] = ['assets/avatar_1.jfif', 'assets/avatar_2.png', 'assets/avatar_3.jpg', ''];
  categoriesList: Category[] = [];
  selectedCategories: Category[] = [];
  subscriptions : Subscription[] = [];
  tempSubscriptions : Subscription[] = [];
  user! : User;

  constructor(private userService : UserService, private programService : ProgramService, private subsService : SubscriptionService) {
    if (userService.activeUser !== null){
      this.user = userService.activeUser;
    }
    this.programService.getCategories().subscribe(response => {
      this.categoriesList = response;
      this.categoriesList.forEach(cat => {
        cat.selected = false;
      });
    });
    this.subsService.getSelectedSubscriptions(this.user).subscribe(response => {
      response.forEach(subs => {
        let cat = this.categoriesList.find(kategorija => kategorija.id == subs.category.id);
        if (cat != null){
          cat.selected = true;
        }
      });
      this.subscriptions = response;
      this.tempSubscriptions = this.subscriptions;
    })
  }

  onCheckboxChange(item: any) {
    item.selected = !item.selected;
    if (item.selected && !this.tempSubscriptions.find(sub => sub.category.id === item.id)){
      let pretplata = this.subscriptions.find(sub => sub.category.id === item.id);
      if (pretplata != null)
          this.tempSubscriptions.push(pretplata);
      else {
        let sub = new Subscription();
        sub.id = -1;
        sub.category = item;
        sub.user = this.user;
        this.tempSubscriptions.push(sub);
      }
    }
    else if (!item.selected) {
     this.tempSubscriptions = this.tempSubscriptions.filter(sub => sub.category.id !== item.id);
    }
  }


  onUpdateUser(updateUserForm: NgForm) {
    // obrisi stare pretplate
    this.subscriptions.forEach(oldSub => {
      let sub = this.tempSubscriptions.find(sub => sub.id === oldSub.id);
      if (!sub)
        this.subsService.deleteSubscription(oldSub.id).subscribe();
    });
    this.tempSubscriptions.forEach(sub => {
      if (sub.id === -1){
        this.subsService.createSubscription(sub).subscribe(response => {
          this.subscriptions.push(response);
        });
      }
    });

    this.userService.updateUser(this.user).subscribe(response => {
      console.log(response);
      alert("Uspješno ste promijenili svoje podatke!");
    });
}

}
