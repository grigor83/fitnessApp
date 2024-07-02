import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { FitnessProgram } from '../../models/fitness-program';
import { MatFormField } from '@angular/material/form-field';
import { FormsModule, NgForm } from '@angular/forms';
import { DatePipe, NgFor } from '@angular/common';
import { Participation } from '../../models/participation';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-paying-form',
  standalone: true,
  imports: [MatDialogModule, MatFormField, FormsModule, NgFor],
  providers: [DatePipe],
  templateUrl: './paying-form.component.html',
  styleUrl: './paying-form.component.css'
})
export class PayingFormComponent {

  items: string[] = ['kartica', 'paypal', 'lično'];
  selectedValue: string = '';

  constructor (private userService : UserService, private dialogRef: MatDialogRef<PayingFormComponent>,
    @Inject(MAT_DIALOG_DATA) public fitnessProgram: FitnessProgram, public datepipe: DatePipe) { }

    payProgram(payingForm: NgForm) {
      if (this.selectedValue != 'lično' && this.selectedValue != ''){
        if (this.userService.activeUser?.cardNumber != payingForm.value.brojKartice){
          alert("Broj kartice nije validan!");
          return;
        }
      }

      let currentDateTime = this.datepipe.transform(new Date(), 'dd.MM.yyyy. HH:mm');
        const newParticipation = new Participation(this.userService.activeUser, this.fitnessProgram, currentDateTime, this.selectedValue);
        this.userService.payProgram(newParticipation).subscribe(response => {
          this.dialogRef.close();
        });
    }

}
