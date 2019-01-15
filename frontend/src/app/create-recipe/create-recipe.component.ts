import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from '../recipe';
import { DIFFICULTIES} from '../difficulty';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.css']
})
export class CreateRecipeComponent implements OnInit {
  recipe: Recipe = new Recipe();
  difficulties = DIFFICULTIES;
  constructor(private recipeService: RecipeService) { }

  ngOnInit() {

  }

  @Input()
  set irecipe(irecipe: Recipe) {
    this.recipe = irecipe;
  }

  save(){
    if(this.recipe.id == null)
      this.recipeService.save(this.recipe);
    else
      this.recipeService.update(this.recipe);
  }

}
