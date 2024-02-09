import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectButtonsComponent } from './select-buttons.component';

describe('SelectButtonsComponent', () => {
  let component: SelectButtonsComponent;
  let fixture: ComponentFixture<SelectButtonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelectButtonsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SelectButtonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
