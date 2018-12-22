import { Macros } from './macros';

export class Food {
  id: number;
  description: string;
  category: string;
  macros: Macros;

  static fromJSON(data:any): Food {
    let f: Food = new Food();
    f.id = data.id;
    f.description = data.description;
    f.category = data.category;
    f.macros = Object.assign(new Macros(), data.macros);
    return f;
  }
}
