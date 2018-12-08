import { Component, OnInit } from '@angular/core';
import { Recipe } from '../recipe';
import { DIFFICULTIES} from '../difficulty';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.css']
})
export class CreateRecipeComponent implements OnInit {
  recipe: Recipe;
  difficulties = DIFFICULTIES;
  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
    this.recipe = new Recipe();
    this.recipe.title = "New recipe";
  }

  save(){
    this.recipeService.save(this.recipe);
  }

}
