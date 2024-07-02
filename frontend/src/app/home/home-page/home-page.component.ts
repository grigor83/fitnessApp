import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { NgFor, NgIf } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { FitnessProgramCardComponent } from '../fitness-program-card/fitness-program-card.component';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { RssFeedComponent } from '../rss-feed/rss-feed.component';
import { FitnessProgram } from '../../models/fitness-program';
import { ProgramService } from '../../services/program.service';
import { SharedService } from '../../services/shared.service';
import { RssService } from '../../services/rss.service';
import { Category } from '../../models/category';
import { ExercisesComponent } from '../exercises/exercises.component';
import { ExercisesService } from '../../services/exercises.service';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-home-app',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatTableModule, MatPaginatorModule, MatSelectModule, FormsModule,
           NgFor, NgIf, NgxPaginationModule, FitnessProgramCardComponent, RssFeedComponent, ExercisesComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

  @ViewChild('filterInput') filterInput!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  dataSource!: MatTableDataSource<FitnessProgram>;
  pageSize = 5;
  currentPage = 0;
  totalElements!: number;
  columnsToDisplay = ['fitnes programi'];
  data : FitnessProgram[] = [];
  resetSelect! : string;
  categoriesList: Category[] = [];
  distinctCategoriesList: Category[] = [];
  rssFeedData : any | null;
  exercises : any | null;
  
  constructor(private programService: ProgramService, private userService : UserService, private sharedService: SharedService, 
               private rssService : RssService, private exercisesService : ExercisesService, private router : Router) { }

  ngOnInit(): void {
    this.sharedService.hideBackButton(true);
    this.sharedService.hideLoginButton(false);
    if (this.userService.signedIn)
      this.sharedService.hideLoginButton(true);

    this.loadPrograms();
    this.programService.getCategories().subscribe(response => {
      this.categoriesList = response;
      this.distinctCategoriesList = this.categoriesList.filter((item, index, self) =>
        index === self.findIndex((t) => t.categoryName === item.categoryName)
      );
    });

    this.loadRSS();
    this.loadExercises();
  }

  loadPrograms() {
    this.programService.getPrograms(this.currentPage, this.pageSize)
      .subscribe(response => {
        this.dataSource = new MatTableDataSource<FitnessProgram>(response.content);
        //this.dataSource = response.content;
        this.totalElements = response.totalElements;
        //this.dataSource.paginator = this.paginator;
      });

      this.programService.getProgramsAsPlainArray(this.currentPage, this.pageSize)
      .subscribe(response => {
        // data sadrzi fitnes programe kao cisti niz podataka
        this.data = response.content;
        //this.totalElements = response.totalElements;
      });
  }

  loadRSS(){
    if (this.rssService.rssFeedData == null){
      this.rssService.getRssFeed().subscribe((response : any) => {
        this.rssService.parseRssFeed(response).then(parsedData =>{
          this.rssService.rssFeedData = parsedData.items;
          this.rssFeedData = this.rssService.rssFeedData;
        })
      });
    }
    this.rssFeedData = this.rssService.rssFeedData;
  }

  loadExercises() {
    if (this.exercisesService.exercises == null){
      this.exercisesService.getExercises().subscribe((response : any) => {
        this.exercisesService.exercises = response;
        this.exercises = this.exercisesService.exercises;
      }); 
    }  
    this.exercises = this.exercisesService.exercises;
  }

  pageChanged(event : PageEvent) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadPrograms();
    this.clearFilter();
  }

  selectedProgram(program: FitnessProgram) {
    // Za prosljeđivanje objekta kao parametara rute, takav nacin nije bezbjedan
    //const queryParams = { item: JSON.stringify(program) };
    //this.router.navigate(['/fitnes-program'], { queryParams });
    // Drugi nacin je sigurniji i on koristi sharedService da bi razmijenio podatke izmedju komponenti koje nisu u odnosu parent-child
    this.sharedService.setFitnessProgram(program);
    this.router.navigate(['/fitness-program']);
  }

  filterByKategorija(filterValue: string) {
    if (filterValue == 'poništi'){
      this.clearFilter();
      return;
    }

    this.dataSource.filterPredicate = (data: FitnessProgram, filter: string) => {
      const categoryMatch = data.category.categoryName.toLowerCase().includes(filter);
      return categoryMatch;
    };
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  filterByAtribut(filterValue: string) {
    if (filterValue == 'poništi'){
      this.clearFilter();
      return;
    }
    
    this.dataSource.filterPredicate = (data: FitnessProgram, filter: string) => {
      const atributMatch = data.category.attribute.attributeName.toLowerCase().includes(filter);
      return atributMatch;
    };
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  // Ovo je primjer kako filtrirati tabelu na osnovu dva parametra, ne koristim ovdje
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filterPredicate = (data: FitnessProgram, filter: string) => {
      const nameMatch = data.programName.toLowerCase().includes(filter);
      const categoryMatch = data.category.categoryName.toLowerCase().includes(filter);
      return nameMatch || categoryMatch;
    };
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  clearFilter() {
    this.resetSelect = '';
    this.dataSource.filter = '';
  }

}
