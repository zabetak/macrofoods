import { Component, OnInit, Input } from '@angular/core';
import { Ingredient } from '../ingredient';
import { Food } from '../food';
import { IngredientGroup } from '../ingredient-group';



@Component({
  selector: 'app-ingredients',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.css']
})
export class IngredientsComponent implements OnInit {
  groups: IngredientGroup[] = [];

  constructor() { }

  ngOnInit() {
  }

  @Input()
  set food(iFood: Food) {
      if(iFood != null){
        this.groups[this.groups.length-1].add(iFood);
      }
  }

  addGroup(): void {
    this.groups.push(new IngredientGroup());
  }

}
