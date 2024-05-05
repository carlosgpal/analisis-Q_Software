import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MappingMeasuresComponent } from './mapping-measures.component';

describe('MappingMeasuresComponent', () => {
  let component: MappingMeasuresComponent;
  let fixture: ComponentFixture<MappingMeasuresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MappingMeasuresComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MappingMeasuresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
