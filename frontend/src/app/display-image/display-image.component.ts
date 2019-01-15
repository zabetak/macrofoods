import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Image } from '../image';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-display-image',
  templateUrl: './display-image.component.html',
  styleUrls: ['./display-image.component.css']
})
export class DisplayImageComponent implements OnInit {

  data: string;

  @Output()
  loadedImage:EventEmitter<Image> = new EventEmitter<Image>();

  constructor(private recipeService: RecipeService) {

  }

  ngOnInit() {
  }

  @Input()
  set image(image:Image) {
    if(image.data==null){
      this.recipeService.loadImage(image).subscribe(imgData => {
        this.data=imgData.data;
        this.loadedImage.emit(imgData);
      });
    } else {
      this.data=image.data;
    }
  }

}
