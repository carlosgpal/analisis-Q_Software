import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatternCreatingEntanglementComponent } from './pattern-creating-entanglement.component';

describe('PatternCreatingEntanglementComponent', () => {
  let component: PatternCreatingEntanglementComponent;
  let fixture: ComponentFixture<PatternCreatingEntanglementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatternCreatingEntanglementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PatternCreatingEntanglementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
