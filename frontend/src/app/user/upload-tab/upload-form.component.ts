import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { FitnessProgram } from '../../models/fitness-program';
import { ProgramService } from '../../services/program.service';
import { NgFor, NgIf } from '@angular/common';
import { MatDialogModule } from '@angular/material/dialog';
import { Kategorija } from '../../models/kategorija';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-upload-form',
  standalone: true,
  imports: [FormsModule, NgIf, MatDialogModule, NgFor],
  templateUrl: './upload-form.component.html',
  styleUrl: './upload-form.component.css'
})
export class UploadFormComponent {

  selectedFile!: File;
  isImageSelected : boolean = true;
  categoriesList: Kategorija[] = [];
  selectedOption!: string | null;

  constructor(private userService : UserService, private programService : ProgramService) { 
    this.programService.getCategories().subscribe(response => {
      this.categoriesList = response;
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    if (this.selectedFile)
          this.isImageSelected = true;
  }

  uploadProgram(event: Event, form : NgForm ) {
    event.preventDefault();  // sprecava uobicajene ponasanje forme(da zatvori mat-dialog)
    if (this.selectedFile == null){
      this.isImageSelected = false;
      return;
    }

    const newProgram = new FitnessProgram(form.value.nazivPrograma, form.value.opis, this.selectedFile.name, this.categoriesList[form.value.kategorija-1], form.value.lokacija, 
                                    form.value.trajanjeTreninga, form.value.nivoTezine, form.value.cijena, form.value.instruktor, 
                                    form.value.kontakt, this.userService.activeUser);
    console.log(newProgram);

    this.programService.uploadProgramAndImage(newProgram, this.selectedFile).subscribe(response => {
      console.log(response);
    });

    form.reset();
    alert("Uspješno ste kreirali novi program!")
  }

  resetForm(form: any) {
    form.reset(); // Reset the form
    this.selectedOption = ''; // Reset the select element
  }

}
