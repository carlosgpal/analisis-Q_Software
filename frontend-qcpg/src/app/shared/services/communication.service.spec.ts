import { TestBed } from '@angular/core/testing';

import { GraphCommunicationService } from './communication.service';

describe('CommunicationService', () => {
  let service: GraphCommunicationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GraphCommunicationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
