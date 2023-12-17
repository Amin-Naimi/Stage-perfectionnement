import { TestBed } from '@angular/core/testing';

import { MarqueeServiceService } from './marquee-service.service';

describe('MarqueeServiceService', () => {
  let service: MarqueeServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MarqueeServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
