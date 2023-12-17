import { TestBed } from '@angular/core/testing';

import { StagaireService } from './stagaire.service';

describe('StagaireService', () => {
  let service: StagaireService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StagaireService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
