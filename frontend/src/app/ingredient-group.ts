import { Food } from './food';
import { Ingredient } from './ingredient';
import { Macros } from './macros';

export class IngredientGroup {
  name: string = "Ingredients";
  ingredients: Ingredient[] = [];
  macros: Macros;

  static fromJSON(data:any):IngredientGroup {
    let group : IngredientGroup = new IngredientGroup();
    group.ingredients = data.ingredients.map(d => Ingredient.fromJSON(d));
    group.calculateSums();
    console.log(group);
    return group;
  }

  add(iFood: Food){
    let ing: Ingredient = new Ingredient();
    ing.amount = 100;
    ing.food = iFood;
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
    let m: Macros = new Macros();
    this.ingredients.forEach((ing:Ingredient) => {
      m = m.add(ing.macros());
    });
    this.macros = m;
  }
}
