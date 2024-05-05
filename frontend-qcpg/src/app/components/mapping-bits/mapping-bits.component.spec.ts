import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MappingBitsComponent } from './mapping-bits.component';

describe('MappingBitsComponent', () => {
  let component: MappingBitsComponent;
  let fixture: ComponentFixture<MappingBitsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MappingBitsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MappingBitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
