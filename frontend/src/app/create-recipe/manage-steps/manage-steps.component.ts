import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from '../../recipe';

@Component({
  selector: 'app-manage-steps',
  templateUrl: './manage-steps.component.html',
  styleUrls: ['./manage-steps.component.css']
})
export class ManageStepsComponent implements OnInit {
    @Input()
    recipe: Recipe;

    constructor() { }

    ngOnInit() {
    }

  }
