import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphControlsComponent } from './graph-controls.component';

describe('GraphControlsComponent', () => {
  let component: GraphControlsComponent;
  let fixture: ComponentFixture<GraphControlsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GraphControlsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GraphControlsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
