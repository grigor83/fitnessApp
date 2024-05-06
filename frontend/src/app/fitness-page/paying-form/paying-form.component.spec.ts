import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayingFormComponent } from './paying-form.component';

describe('PayingFormComponent', () => {
  let component: PayingFormComponent;
  let fixture: ComponentFixture<PayingFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PayingFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PayingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
