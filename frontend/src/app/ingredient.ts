import { Food } from './food';
import { NutritionalFacts } from './nutritional-facts';

export class Ingredient {
  amount: number;
  food: Food;

  static fromJSON(data:any): Ingredient {
      let i:Ingredient = new Ingredient();
      i.amount = data.amount;
      i.food = Food.fromJSON(data.food);
      return i;
  }

  facts(): NutritionalFacts {
    return this.food.facts.multiplyBy(this.amount/100);
  }

}
