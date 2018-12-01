import { Food } from './food';
import { Ingredient } from './ingredient';

export class IngredientGroup {
  id: number;
  name: string = "Ingredients";
  seq: number;
  ingredients: Ingredient[] = [];
  calTotal: number = 0;
  protTotal: number = 0;
  carbTotal: number = 0;
  fatTotal: number = 0;

  add(iFood: Food){
    let ing: Ingredient = {
        id: -1,
        seq: this.ingredients.length,
        amount: 100,
        food: iFood
    };
    this.ingredients.push(ing);
    this.calculateSums();
  }

  delete(iFood: Food){
    for(let i = 0; i < this.ingredients.length; i++){
      if ( this.ingredients[i].food === iFood) {
        this.ingredients.splice(i, 1);
        this.calculateSums();
      }
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
