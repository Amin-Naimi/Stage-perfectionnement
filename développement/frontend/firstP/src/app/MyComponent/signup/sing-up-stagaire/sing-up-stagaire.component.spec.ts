import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingUpStagaireComponent } from './sing-up-stagaire.component';

describe('SingUpStagaireComponent', () => {
  let component: SingUpStagaireComponent;
  let fixture: ComponentFixture<SingUpStagaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingUpStagaireComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SingUpStagaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
