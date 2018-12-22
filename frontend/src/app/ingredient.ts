import { Food } from './food';
import { Macros } from './macros';

export class Ingredient {
  amount: number;
  food: Food;

  static fromJSON(data:any): Ingredient {
      let i:Ingredient = new Ingredient();
      i.amount = data.amount;
      i.food = Food.fromJSON(data.food);
      return i;
  }

  macros(): Macros {
    return this.food.macros.multiplyBy(this.amount/100);
  }

}
