import { IngredientGroup } from './ingredient-group';
import { StepGroup } from './step-group';

export class Recipe {
  id: number;
  title: string;
  summary: string;
  conclusion: string;
  prepTime: number;
  cookTime: number;
  groups: IngredientGroup[] = [];
  stepGroups: StepGroup[] = [];
  image: string;

  addGroup(): void {
    this.groups.push(new IngredientGroup());
  }

  deleteGroup(iGroup:IngredientGroup){
    for(let i = 0; i < this.groups.length; i++){
      if ( this.groups[i] === iGroup) {
        this.groups.splice(i, 1);
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
