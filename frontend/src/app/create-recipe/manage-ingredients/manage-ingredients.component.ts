import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from '../../recipe';

@Component({
  selector: 'app-manage-ingredients',
  templateUrl: './manage-ingredients.component.html',
  styleUrls: ['./manage-ingredients.component.css']
})
export class ManageIngredientsComponent implements OnInit {
  @Input()
  recipe: Recipe;

  constructor() { }

  ngOnInit() {
  }

}
