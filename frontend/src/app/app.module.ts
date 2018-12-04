import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'
import { HttpClientModule }    from '@angular/common/http';

import { AppComponent } from './app.component';
import { FoodsComponent } from './foods/foods.component';
import { FoodsService } from './foods.service';
import { ImageUploadComponent } from './image-upload/image-upload.component';
import { CreateRecipeComponent } from './create-recipe/create-recipe.component';
import { ManageIngredientsComponent } from './create-recipe/manage-ingredients/manage-ingredients.component';
import { ManageStepsComponent } from './create-recipe/manage-steps/manage-steps.component';


@NgModule({
  declarations: [
    AppComponent,
    FoodsComponent,
    ImageUploadComponent,
    CreateRecipeComponent,
    ManageIngredientsComponent,
    ManageStepsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    FoodsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
