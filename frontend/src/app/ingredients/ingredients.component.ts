import { Component, OnInit } from '@angular/core';
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

  addFood(iGroup:IngredientGroup,iFood: Food) {
      if(iFood != null){
        iGroup.add(iFood);
      }
  }

  addGroup(): void {
    this.groups.push(new IngredientGroup());
  }

  deleteGroup(iGroup:IngredientGroup){
    for(let i = 0; i < this.groups.length; i++){
      if ( this.groups[i] === iGroup) {
        this.groups.splice(i, 1);
      }
    }
  }

  deleteIngredient(gname:string,iFood:Food){
    this.groups.forEach(group => {
      if(group.name == gname){
        group.delete(iFood);
      }
    });
  }

}
