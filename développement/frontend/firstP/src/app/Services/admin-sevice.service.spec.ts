import { TestBed } from '@angular/core/testing';

import { AdminSeviceService } from './admin-sevice.service';

describe('AdminSeviceService', () => {
  let service: AdminSeviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminSeviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
