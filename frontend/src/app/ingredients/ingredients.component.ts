import { Component, OnInit, Input } from '@angular/core';
import { Food } from '../food';



@Component({
  selector: 'app-ingredients',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.css']
})
export class IngredientsComponent implements OnInit {

  ingredients: Food[] = [];
  calTotal: number = 0;
  protTotal: number = 0;
  carbTotal: number = 0;
  fatTotal: number = 0;

  constructor() { }

  ngOnInit() {
  }

  @Input()
  set food(food: Food) {
      if(food != null){
        this.ingredients.push(Object.assign({}, food));
        this.calculateSums();
      }
  }

  calculateSums(): void {
    this.calTotal = 0;
    this.protTotal = 0;
    this.carbTotal = 0;
    this.fatTotal = 0;

    this.ingredients.forEach((ing:Food) => {
        this.calTotal += ing.kcal * (ing.qty/100);
        this.protTotal += ing.protein * (ing.qty/100) ;
        this.carbTotal += ing.carbohydrate * (ing.qty/100);
        this.fatTotal += ing.fat * (ing.qty/100);
    });
  }

}
