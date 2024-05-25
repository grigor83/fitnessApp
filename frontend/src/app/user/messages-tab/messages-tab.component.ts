import { DatePipe, NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { Message } from '../../models/message';
import { MessageFormComponent } from '../message-form/message-form.component';

@Component({
  selector: 'app-messages-tab',
  standalone: true,
  imports: [MatDialogModule, FormsModule, NgFor, NgIf, MessageFormComponent],
  providers: [DatePipe],
  templateUrl: './messages-tab.component.html',
  styleUrl: './messages-tab.component.css'
})
export class MessagesTabComponent {

  receiversList: string[] = ['korisniku', 'savjetniku'];
  usersList: User[] = [];
  counselorList: User[] = [];
  selectedOption: string = 'korisniku';
  selectedReceiver: User | null = null;
  message: string[] = [];
  receivedMessages: Message[] | undefined = [];

  constructor(private userService : UserService, public datepipe: DatePipe, 
              private dialog : MatDialog) {}

  filterUsers() {
    this.userService.getAllUsers().subscribe(response => {
      this.usersList = response.filter(user => !user.savjetnik);
      this.usersList = this.usersList.filter(user => user.korisnikId!=this.userService.activeUser?.korisnikId);
      this.counselorList = response.filter(user => user.savjetnik);
    });
    this.userService.getUsersMessages(this.userService.activeUser).subscribe(response => {
      this.receivedMessages = response;
    });
    //this.receivedMessages = this.userService.activeUser?.primljenePoruke;
  }

  sendMessage(event: Event, messagesForm : NgForm) {
    let message = new Message();
    message.posiljalac = this.userService.activeUser;
    message.primalac = this.selectedReceiver;
    message.tekst = this.message;
    message.datum = this.datepipe.transform(new Date(), 'dd.MM.yyyy. HH:mm');
    this.userService.sendMessage(message).subscribe(response => {
    });
    this.message = [];
    this.resetForm(messagesForm);
    alert("Poslali ste poruku!")
  }

  resetForm(messagesForm: any) {
    messagesForm.reset(); // Reset the form
    this.selectedOption = ''; // Reset the select element
    this.selectedReceiver = null;
    messagesForm.value.message = '';
  }

  onSelectChange(event: any) {
    this.selectedOption = event.target.value;
    this.filterUsers();
  }

  onListItemClick(receiver: User) {
    this.selectedReceiver = receiver;
  }

  onMessageClick(message: Message) {
    const dialogRef = this.dialog.open(MessageFormComponent, {
      width: '420px',
      data: message,
    });
  }

}
