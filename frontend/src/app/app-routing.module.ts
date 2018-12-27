import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateRecipeComponent } from './create-recipe/create-recipe.component';
import { TopRecipesComponent } from './top-recipes/top-recipes.component';
import { RecipeDetailsComponent } from './recipe-details/recipe-details.component';

const routes: Routes = [
  { path: 'recipes/new', component: CreateRecipeComponent },
  { path: 'recipes/top', component: TopRecipesComponent } ,
  { path: 'recipes/:id', component: RecipeDetailsComponent }  
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
