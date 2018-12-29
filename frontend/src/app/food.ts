import { NutritionalFacts } from './nutritional-facts';

export class Food {
  id: number;
  description: string;
  category: string;
  facts: NutritionalFacts;

  static fromJSON(data:any): Food {
    let f: Food = new Food();
    f.id = data.id;
    f.description = data.description;
    f.category = data.category;
    f.facts = NutritionalFacts.fromJSON(data.facts);
    return f;
  }
}
