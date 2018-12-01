import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'
import { HttpClientModule }    from '@angular/common/http';

import { AppComponent } from './app.component';
import { FoodsComponent } from './foods/foods.component';
import { FoodsService } from './foods.service';
import { IngredientsComponent } from './ingredients/ingredients.component';
import { ImageUploadComponent } from './image-upload/image-upload.component';
import { StepsComponent } from './steps/steps.component';


@NgModule({
  declarations: [
    AppComponent,
    FoodsComponent,
    IngredientsComponent,
    ImageUploadComponent,
    StepsComponent
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
