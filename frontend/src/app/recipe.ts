import { Difficulty } from './difficulty';
import { IngredientGroup } from './ingredient-group';
import { StepGroup } from './step-group';
import { Tag } from './tag';
import { Macros } from './macros';

export class Recipe {
  id: number;
  title: string;
  summary: string;
  conclusion: string;
  prepTime: number;
  cookTime: number;
  difficulty: number = 0;
  servings: number = 2;
  ingGroups: IngredientGroup[] = [];
  stepGroups: StepGroup[] = [];
  image: string;
  tags: Tag[] = [];
  macros: Macros;

  static fromJSON(data: any): Recipe {
    console.log(data);
    let r: Recipe = new Recipe();
    r.id = data.id;
    r.title = data.title;
    r.summary = data.summary;
    r.conclusion = data.conclusion;
    r.prepTime = data.prepTime;
    r.cookTime = data.cookTime;
    r.difficulty = data.difficulty;
    r.servings = data.servings;
    if(data.ingGroups != null)
      r.ingGroups = data.ingGroups.map(d => IngredientGroup.fromJSON(d));
    if(data.stepGroups != null)
      r.stepGroups = data.stepGroups.map(d => StepGroup.fromJSON(d));
    r.image = data.image;
    if(data.tags != null)
      r.tags = data.tags.map(d => Tag.fromJSON(d));
    r.macros = r.computeMacros();
    return r;
  }

  addIngredientGroup(): void {
    this.ingGroups.push(new IngredientGroup());
  }

  deleteIngredientGroup(iGroup:IngredientGroup){
    for(let i = 0; i < this.ingGroups.length; i++){
      if ( this.ingGroups[i] === iGroup) {
        this.ingGroups.splice(i, 1);
      }
    }
  }

  addStepGroup(): void {
    this.stepGroups.push(new StepGroup());
  }

  deleteStepGroup(iGroup:StepGroup): void {
    for(let i = 0; i < this.stepGroups.length; i++){
      if ( this.stepGroups[i] === iGroup) {
        this.stepGroups.splice(i, 1);
      }
    }
  }

  addTag(iTag: Tag): void {
    this.tags.push(iTag);
  }

  deleteTag(iTag: Tag): void {
    for(let i = 0; i < this.tags.length; i++){
      if ( this.tags[i] === iTag) {
        this.tags.splice(i, 1);
      }
    }
  }

  private computeMacros(): Macros {
    let m: Macros = new Macros();
    this.ingGroups.forEach(ig => {
      m = m.add(ig.macros);
    })
    return m;
  }
}
