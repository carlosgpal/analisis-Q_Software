import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoreinfoCreatingEntanglementComponent } from './moreinfo-creating-entanglement.component';

describe('MoreinfoCreatingEntanglementComponent', () => {
  let component: MoreinfoCreatingEntanglementComponent;
  let fixture: ComponentFixture<MoreinfoCreatingEntanglementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MoreinfoCreatingEntanglementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MoreinfoCreatingEntanglementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
