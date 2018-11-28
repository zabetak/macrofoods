import { Component, OnInit, Input } from '@angular/core';
import { Ingredient } from '../ingredient';
import { Food } from '../food';



@Component({
  selector: 'app-ingredients',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.css']
})
export class IngredientsComponent implements OnInit {

  ingredients: Ingredient[] = [];
  calTotal: number = 0;
  protTotal: number = 0;
  carbTotal: number = 0;
  fatTotal: number = 0;

  constructor() { }

  ngOnInit() {
  }

  @Input()
  set food(iFood: Food) {
      if(iFood != null){
        let ing: Ingredient = {
            id: -1,
            seq: this.ingredients.length,
            amount: 100,
            food: iFood
        };
        this.ingredients.push(ing);
        this.calculateSums();
      }
  }

  calculateSums(): void {
    this.calTotal = 0;
    this.protTotal = 0;
    this.carbTotal = 0;
    this.fatTotal = 0;

    this.ingredients.forEach((ing:Ingredient) => {
        this.calTotal += ing.food.kcal * (ing.amount/100);
        this.protTotal += ing.food.protein * (ing.amount/100) ;
        this.carbTotal += ing.food.carbohydrate * (ing.amount/100);
        this.fatTotal += ing.food.fat * (ing.amount/100);
    });
  }

}
