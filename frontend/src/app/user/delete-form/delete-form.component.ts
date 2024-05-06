import { Component } from '@angular/core';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-form',
  standalone: true,
  imports: [MatDialogModule],
  templateUrl: './delete-form.component.html',
  styleUrl: './delete-form.component.css'
})
export class DeleteFormComponent {

  constructor(private dialogRef: MatDialogRef<DeleteFormComponent>) {}

  close() {
    this.dialogRef.close();
  }


  confirm() {
    this.dialogRef.close(true);
  }
}
