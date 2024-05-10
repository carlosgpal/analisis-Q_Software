import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoreInfoGatesComponent } from './more-info-gates.component';

describe('MoreInfoGatesComponent', () => {
  let component: MoreInfoGatesComponent;
  let fixture: ComponentFixture<MoreInfoGatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MoreInfoGatesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MoreInfoGatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
