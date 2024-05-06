import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramsTabComponent } from './programs-tab.component';

describe('ProgramsTabComponent', () => {
  let component: ProgramsTabComponent;
  let fixture: ComponentFixture<ProgramsTabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProgramsTabComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProgramsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
