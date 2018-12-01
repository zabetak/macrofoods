import { Step } from './step';

export class StepGroup {
  id: number;
  name: string = "Steps";
  seq: number;
  steps: Step[] = [];

  add(iStep: Step){
    this.steps.push(iStep);
  }

  delete(iStep: Step){
    for(let i = 0; i < this.steps.length; i++){
      if ( this.steps[i] === iStep) {
        this.steps.splice(i, 1);
      }
    }
  }

}
