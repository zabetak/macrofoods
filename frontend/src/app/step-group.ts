import { Step } from './step';

export class StepGroup {
  id: number;
  name: string = "Steps";
  seq: number;
  steps: Step[] = [];

  addNewStep(): void{
    this.steps.push(new Step());
  }

  delete(iStep: Step){
    for(let i = 0; i < this.steps.length; i++){
      if ( this.steps[i] === iStep) {
        this.steps.splice(i, 1);
      }
    }
  }

}
