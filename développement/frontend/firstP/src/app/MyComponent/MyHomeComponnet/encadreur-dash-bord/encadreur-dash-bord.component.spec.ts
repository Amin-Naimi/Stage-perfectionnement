import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EncadreurDashBordComponent } from './encadreur-dash-bord.component';

describe('EncadreurDashBordComponent', () => {
  let component: EncadreurDashBordComponent;
  let fixture: ComponentFixture<EncadreurDashBordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EncadreurDashBordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EncadreurDashBordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
