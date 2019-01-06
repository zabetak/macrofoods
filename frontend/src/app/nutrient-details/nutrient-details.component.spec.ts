import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NutrientDetailsComponent } from './nutrient-details.component';

describe('NutrientDetailsComponent', () => {
  let component: NutrientDetailsComponent;
  let fixture: ComponentFixture<NutrientDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NutrientDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NutrientDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
