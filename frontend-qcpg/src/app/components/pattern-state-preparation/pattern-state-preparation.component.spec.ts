import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatternStatePreparationComponent } from './pattern-state-preparation.component';

describe('PatternStatePreparationComponent', () => {
  let component: PatternStatePreparationComponent;
  let fixture: ComponentFixture<PatternStatePreparationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatternStatePreparationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PatternStatePreparationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
