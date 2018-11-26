import { Component, OnInit } from '@angular/core';
import { Food } from '../food';
import { FoodsService } from '../foods.service';
import { Observable } from 'rxjs/Observable';
import { Subject }    from 'rxjs/Subject';
import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

@Component({
  selector: 'app-foods',
  templateUrl: './foods.component.html',
  styleUrls: ['./foods.component.css']
})
export class FoodsComponent implements OnInit {
  foods: Observable<Food[]>;
  selectedFood: Food;

  private searchTerms = new Subject<string>();

  constructor(private foodService: FoodsService) { }

  ngOnInit(): void {
    this.foods = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.foodService.searchFoods(term)),
    );
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  onSelect(food: Food): void {
      this.selectedFood = food;
  }

}
