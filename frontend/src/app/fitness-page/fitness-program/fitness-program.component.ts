import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { KomentarComponent } from '../komentar/komentar.component';
import { FitnessProgram } from '../../models/fitness-program';
import { ProgramService } from '../../services/program.service';
import { SharedService } from '../../services/shared.service';
import { CommentFormComponent } from '../comment-form/comment-form.component';
import { PayingFormComponent } from '../paying-form/paying-form.component';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-fitness-program',
  standalone: true,
  imports: [FormsModule, NgFor, KomentarComponent, NgIf, NgClass],
  templateUrl: './fitness-program.component.html',
  styleUrl: './fitness-program.component.css'
})
export class FitnessProgramComponent {

  fitnesProgram! : FitnessProgram;
  showComments : boolean = false;

  constructor(private route: ActivatedRoute,private programService : ProgramService, private sharedService: SharedService,
                private userService : UserService, private dialog : MatDialog){
  }

  ngOnInit(): void {
    this.fitnesProgram = this.sharedService.getFitnessProgram();
    this.sharedService.hideBackButton(false);
    if (this.userService.signedIn){
      this.sharedService.hideLoginButton(true);  
      this.showComments = true;
    }
  }

  openCommentDialog() {
    const dialogRef = this.dialog.open(CommentFormComponent, {
      width: '420px', // Specify the width of the dialog. Optionally, you can set other properties like height, minWidth, etc.
      data: this.fitnesProgram,
    });
    // Dohvati program sa servera kako bi se osvjezili komentari i prikazali novi komentari
    dialogRef.afterClosed().subscribe(result => {
      if (result != null && result.komentar != null){
        this.programService.getProgram(this.fitnesProgram.id).subscribe(response => {
          this.fitnesProgram = response;
        });
      }
    });
  }

  openPayDialog() {
    const dialogRef = this.dialog.open(PayingFormComponent, {
      width: '420px', // Specify the width of the dialog. Optionally, you can set other properties like height, minWidth, etc.
      data: this.fitnesProgram,
    });
  }

}
