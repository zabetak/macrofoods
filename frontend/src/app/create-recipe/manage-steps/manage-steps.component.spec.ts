import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageStepsComponent } from './manage-steps.component';

describe('ManageStepsComponent', () => {
  let component: ManageStepsComponent;
  let fixture: ComponentFixture<ManageStepsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageStepsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageStepsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
