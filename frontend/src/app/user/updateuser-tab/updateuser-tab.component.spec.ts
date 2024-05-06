import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateuserTabComponent } from './updateuser-tab.component';

describe('UpdateuserTabComponent', () => {
  let component: UpdateuserTabComponent;
  let fixture: ComponentFixture<UpdateuserTabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateuserTabComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateuserTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
