import { DatePipe } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { ProgramService } from '../../services/program.service';
import { FitnessProgram } from '../../models/fitness-program';
import { Comment } from '../../models/comment';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-comment-form',
  standalone: true,
  imports: [MatDialogModule, MatFormField, FormsModule],
  providers: [DatePipe],
  templateUrl: './comment-form.component.html',
  styleUrl: './comment-form.component.css'
})
export class CommentFormComponent {

  constructor(private userService : UserService, private programService : ProgramService, private dialogRef: MatDialogRef<CommentFormComponent>,
    @Inject(MAT_DIALOG_DATA) public fitnessProgram: FitnessProgram, public datepipe: DatePipe) {}

    postComment(komentarForm: NgForm) {
      if (komentarForm.value.tekst == null)
        return;
    
      let currentDateTime = this.datepipe.transform(new Date(), 'dd.MM.yyyy. HH:mm');
      const newKomentar = new Comment(this.fitnessProgram, currentDateTime, komentarForm.value.tekst);
      if (this.userService.activeUser != null)
         newKomentar.korisnik = this.userService.activeUser;
      this.programService.uploadComment(newKomentar).subscribe(response => {
        this.dialogRef.close({ komentar : 'postavljen' });
      });
    }

}
