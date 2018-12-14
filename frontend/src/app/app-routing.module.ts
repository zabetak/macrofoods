import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateRecipeComponent } from './create-recipe/create-recipe.component';
import { TopRecipesComponent } from './top-recipes/top-recipes.component';

const routes: Routes = [
  { path: 'recipes/new', component: CreateRecipeComponent },
  { path: 'recipes/top', component: TopRecipesComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
