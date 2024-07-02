import { NgFor, NgIf } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { ProgramService } from '../../services/program.service';
import { FitnessProgram } from '../../models/fitness-program';
import { Category } from '../../models/category';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-edit-form',
  standalone: true,
  imports: [FormsModule, NgIf, MatDialogModule, NgFor],
  templateUrl: './edit-form.component.html',
  styleUrl: './edit-form.component.css'
})
export class EditFormComponent {

  selectedFile!: File;
  isImageSelected : boolean = true;
  program!: FitnessProgram;
  categoriesList: Category[] = [];

  constructor(private userService : UserService, private programService : ProgramService, 
    @Inject(MAT_DIALOG_DATA) public data: FitnessProgram, private dialogRef: MatDialogRef<EditFormComponent>) { 
      if (data){
        this.program = data;
      }
      this.programService.getCategories().subscribe(response => {
        this.categoriesList = response;
      });
    }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    if (this.selectedFile)
          this.isImageSelected = true;
  }

  updateProgram(event: Event, form : NgForm ) {
    event.preventDefault();  // sprecava uobicajene ponasanje forme(da zatvori mat-dialog)
    if (this.selectedFile == null && this.program == null){
      this.isImageSelected = false;
      return;
    }

    this.program.category = this.categoriesList[form.value.kategorija-1];
    // Zbog two-way binding-a, ne moram u varijablu this.user da sacuvam nove vrijednosti iz forme
    this.programService.updateProgram(this.program, this.selectedFile).subscribe(response => {
      console.log(response);
      this.dialogRef.close();
      alert("Uspješno ste izmijenili podatke o programu!")
    });
  }

}
