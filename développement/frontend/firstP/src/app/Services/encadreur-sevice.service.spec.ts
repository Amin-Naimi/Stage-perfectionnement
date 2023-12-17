import { TestBed } from '@angular/core/testing';

import { EncadreurSeviceService } from './encadreur-sevice.service';

describe('EncadreurSeviceService', () => {
  let service: EncadreurSeviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EncadreurSeviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
