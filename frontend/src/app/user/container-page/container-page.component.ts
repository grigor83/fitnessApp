import { Component, ViewChild } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { UploadFormComponent } from '../upload-tab/upload-form.component';
import { ProgramsTabComponent } from '../programs-tab/programs-tab.component';
import { SharedService } from '../../services/shared.service';
import { HistoryTabComponent } from '../history-tab/history-tab.component';
import { UpdateuserTabComponent } from '../updateuser-tab/updateuser-tab.component';

@Component({
  selector: 'app-container-page',
  standalone: true,
  imports: [MatTabsModule, UploadFormComponent, ProgramsTabComponent, HistoryTabComponent, UpdateuserTabComponent],
  templateUrl: './container-page.component.html',
  styleUrl: './container-page.component.css'
})
export class ContainerPageComponent {

  @ViewChild(ProgramsTabComponent) childComponent!: ProgramsTabComponent;
  @ViewChild(HistoryTabComponent) childComponent2!: HistoryTabComponent;

  constructor(private sharedService : SharedService) {}

  ngOnInit(): void {
    this.sharedService.hideBackButton(false);
  }

  changeTab($event: number) {
    if ($event == 0)
      this.childComponent.loadPrograms();
    if ($event == 3)
      this.childComponent2.loadParticipations();
  }
}
