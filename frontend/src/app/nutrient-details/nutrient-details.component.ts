import { Component, OnInit, Input } from '@angular/core';
import { Nutrient } from '../nutrient';

@Component({
  selector: 'app-nutrient-details',
  templateUrl: './nutrient-details.component.html',
  styleUrls: ['./nutrient-details.component.css']
})
export class NutrientDetailsComponent implements OnInit {
  private n: Nutrient;
  @Input()
  useLongNames: boolean = true;

  constructor() { }

  ngOnInit() {
  }

  @Input()
  set nutrient(iNutrient:Nutrient){
    this.n = iNutrient;
  }

  get nutrient(): Nutrient {
    return this.n;
  }
}
