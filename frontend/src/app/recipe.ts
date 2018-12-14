import { Difficulty } from './difficulty';
import { IngredientGroup } from './ingredient-group';
import { StepGroup } from './step-group';
import { Category } from './category';

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
  categories: Category[] = [];

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

}
