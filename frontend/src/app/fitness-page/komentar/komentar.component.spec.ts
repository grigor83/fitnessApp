import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KomentarComponent } from './komentar.component';

describe('KartaComponent', () => {
  let component: KomentarComponent;
  let fixture: ComponentFixture<KomentarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KomentarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(KomentarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
