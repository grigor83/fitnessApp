import { Component, Inject, Input } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Message } from '../../models/message';

@Component({
  selector: 'app-message-form',
  standalone: true,
  imports: [MatCardModule, MatButton],
  templateUrl: './message-form.component.html',
  styleUrl: './message-form.component.css'
})
export class MessageFormComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public message: Message, private dialogRef: MatDialogRef<MessageFormComponent>){ }

  close() {
    this.dialogRef.close();
  }

}
