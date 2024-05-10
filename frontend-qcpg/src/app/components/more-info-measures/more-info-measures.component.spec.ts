import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoreInfoMeasuresComponent } from './more-info-measures.component';

describe('MoreInfoMeasuresComponent', () => {
  let component: MoreInfoMeasuresComponent;
  let fixture: ComponentFixture<MoreInfoMeasuresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MoreInfoMeasuresComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MoreInfoMeasuresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
