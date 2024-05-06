import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { FitnessProgram } from '../../models/fitness-program';
import { MatIconModule } from '@angular/material/icon';
import { NgIf } from '@angular/common';
import { SharedService } from '../../services/shared.service';
import { ProgramService } from '../../services/program.service';
import { MatDialog } from '@angular/material/dialog';
import { DeleteFormComponent } from '../delete-form/delete-form.component';
import { UploadFormComponent } from '../upload-tab/upload-form.component';
import { EditFormComponent } from '../edit-form/edit-form.component';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-programs-tab',
  standalone: true,
  imports: [MatTableModule, MatIconModule, NgIf],
  templateUrl: './programs-tab.component.html',
  styleUrl: './programs-tab.component.css'
})
export class ProgramsTabComponent implements OnInit {

  displayedColumns: string[] = ['name', 'description', 'category', 'location', 'difficulty', 'price', 'instructor', 'contact', 'image', 'delete', 'edit'];
  dataSource!: MatTableDataSource<FitnessProgram>;

  constructor(private sharedService : SharedService, private programService : ProgramService, private userService : UserService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.sharedService.hideBackButton(false);
    this.loadPrograms();
  }

  loadPrograms(){
    this.programService.getAllPrograms()
      .subscribe(response => {
        this.dataSource = new MatTableDataSource<FitnessProgram>(response.filter(program => program.autor?.ime == this.userService.activeUser?.ime));
      });
  }

  delete(element: any) {
    this.dialog.open(DeleteFormComponent, {
      width: '420px'
    })
      .afterClosed()
      .subscribe(result => {
        if (result) {
          this.programService.deleteProgram(element.programId)
            .subscribe(response => {
                        this.loadPrograms();
            })
        }
      });
  }

  edit(program: FitnessProgram) {
    this.dialog.open(EditFormComponent, {
      width: '520px',
      data: program,
    });
  }

}
