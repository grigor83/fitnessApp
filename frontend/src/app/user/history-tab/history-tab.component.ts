import { Component } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Participation } from '../../models/participation';
import { NgIf } from '@angular/common';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-history-tab',
  standalone: true,
  imports: [MatTableModule, NgIf],
  templateUrl: './history-tab.component.html',
  styleUrl: './history-tab.component.css'
})
export class HistoryTabComponent {

  displayedColumns: string[] = ['name', 'description', 'category', 'location', 'difficulty', 'price', 'instructor', 'contact', 'image', 'paying', 'date'];
  dataSource!: MatTableDataSource<Participation>;

  constructor(private userService : UserService) {}


  ngOnInit(): void {
    this.loadParticipations();
  }

  loadParticipations() {
    this.userService.getAllParticipations()
      .subscribe(response => {
        response = response.filter(participation => participation.user?.id == this.userService.activeUser?.id);
        this.dataSource = new MatTableDataSource<Participation>(response);
      });
  }

}
