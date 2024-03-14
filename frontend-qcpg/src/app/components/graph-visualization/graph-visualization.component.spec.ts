import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphVisualizationComponent } from './graph-visualization.component';

describe('GraphVisualitationComponent', () => {
  let component: GraphVisualizationComponent;
  let fixture: ComponentFixture<GraphVisualizationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GraphVisualizationComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(GraphVisualizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
