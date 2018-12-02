import { Component, OnInit } from '@angular/core';
import { Recipe } from '../recipe';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.css']
})
export class CreateRecipeComponent implements OnInit {
  recipe: Recipe;

  constructor() { }

  ngOnInit() {
    this.recipe = new Recipe();
    this.recipe.title = "New recipe";
  }

}
