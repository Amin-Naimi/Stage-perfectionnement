import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginCComponent } from './login-c.component';

describe('LoginCComponent', () => {
  let component: LoginCComponent;
  let fixture: ComponentFixture<LoginCComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginCComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
