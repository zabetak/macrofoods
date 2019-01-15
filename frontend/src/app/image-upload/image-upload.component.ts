import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Image } from '../image';

@Component({
  selector: 'app-image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent implements OnInit {

  @Output()
  imageUpload:EventEmitter<Image> = new EventEmitter<Image>();

  constructor() { }

  ngOnInit() {
  }

  processFile(imageInput: any) {
    const file: File = imageInput.files[0];
    const reader = new FileReader();

    reader.addEventListener('load', (event: any) => {
      let i: Image = new Image();
      i.data = event.target.result;
      this.imageUpload.emit(i);
    });

    reader.readAsDataURL(file);
  }

}
