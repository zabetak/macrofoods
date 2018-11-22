import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule }    from '@angular/common/http';

import { AppComponent } from './app.component';
import { FoodsComponent } from './foods/foods.component';
import { FoodsService } from './foods.service';


@NgModule({
  declarations: [
    AppComponent,
    FoodsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [
    FoodsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
