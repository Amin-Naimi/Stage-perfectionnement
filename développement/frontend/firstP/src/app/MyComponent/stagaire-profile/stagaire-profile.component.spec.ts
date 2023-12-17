import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StagaireProfileComponent } from './stagaire-profile.component';


describe('StagaireProfileComponent', () => {
  let component: StagaireProfileComponent;
  let fixture: ComponentFixture<StagaireProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StagaireProfileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StagaireProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
