import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoreInfoBitsComponent } from './more-info-bits.component';

describe('MoreInfoBitsComponent', () => {
  let component: MoreInfoBitsComponent;
  let fixture: ComponentFixture<MoreInfoBitsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MoreInfoBitsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MoreInfoBitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
