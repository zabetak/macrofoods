import { Step } from './step';

export class StepGroup {
  name: string = "Steps";
  steps: Step[] = [];


  static fromJSON(data:any):StepGroup {
    let group : StepGroup = new StepGroup();
    group.steps = data.steps.map(d => Step.fromJSON(d));
    return group;
  }

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
