import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MappingGatesComponent } from './mapping-gates.component';

describe('MappingGatesComponent', () => {
  let component: MappingGatesComponent;
  let fixture: ComponentFixture<MappingGatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MappingGatesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MappingGatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
