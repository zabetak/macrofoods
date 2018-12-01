import { Component, OnInit } from '@angular/core';
import { StepGroup } from '../step-group';
import { Step } from '../step';

@Component({
  selector: 'app-steps',
  templateUrl: './steps.component.html',
  styleUrls: ['./steps.component.css']
})
export class StepsComponent implements OnInit {

  groups: StepGroup[] = [];

  constructor() { }

  ngOnInit() {
  }

  addGroup(): void {
    this.groups.push(new StepGroup());
  }

  deleteGroup(iGroup:StepGroup){
    for(let i = 0; i < this.groups.length; i++){
      if ( this.groups[i] === iGroup) {
        this.groups.splice(i, 1);
      }
    }
  }

  addStep(group:StepGroup){
      group.add(new Step());
  }

  deleteStep(gname:string,iStep:Step){
    this.groups.forEach(group => {
      if(group.name == gname){
        group.delete(iStep);
      }
    });
  }

}
