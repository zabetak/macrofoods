import { Component, OnInit, Input } from '@angular/core';
import { Nutrient } from '../nutrient';

@Component({
  selector: 'app-nutrient-list',
  templateUrl: './nutrient-list.component.html',
  styleUrls: ['./nutrient-list.component.css']
})
export class NutrientListComponent implements OnInit {
  private list: Nutrient[];

  constructor() { }

  ngOnInit() {
  }

  @Input()
  set nutrients(iNutrient:Nutrient[]){
    this.list = [];
    iNutrient.forEach(n => {
      if(n != null)
        this.list.push(n);
    });
  }

  get nutrients(){
    return this.list;
  }
}
