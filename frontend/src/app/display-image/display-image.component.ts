import { Component, OnInit, Input } from '@angular/core';
import { Image } from '../image';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-display-image',
  templateUrl: './display-image.component.html',
  styleUrls: ['./display-image.component.css']
})
export class DisplayImageComponent implements OnInit {

  data: string;

  constructor(private recipeService: RecipeService) {

  }

  ngOnInit() {
  }

  @Input()
  set image(image:Image) {
    this.recipeService.loadImage(image).subscribe(imgData => {
      this.data=imgData.data;
    });
  }

}
