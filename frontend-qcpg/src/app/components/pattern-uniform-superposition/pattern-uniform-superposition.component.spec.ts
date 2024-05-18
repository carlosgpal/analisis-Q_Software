import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatternUniformSuperpositionComponent } from './pattern-uniform-superposition.component';

describe('PatternUniformSuperpositionComponent', () => {
  let component: PatternUniformSuperpositionComponent;
  let fixture: ComponentFixture<PatternUniformSuperpositionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatternUniformSuperpositionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PatternUniformSuperpositionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
