import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableStagaireComponent } from './table-stagaire.component';

describe('TableStagaireComponent', () => {
  let component: TableStagaireComponent;
  let fixture: ComponentFixture<TableStagaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableStagaireComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableStagaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
