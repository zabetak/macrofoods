export class Step {
  description: string;

  static fromJSON(data:any):Step {
    let step : Step = new Step();
    step.description = data.description;
    return step;
  }

}
