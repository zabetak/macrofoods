import { Component, OnInit, Output, EventEmitter } from '@angular/core';

class ImageSnippet {
  constructor(public src: string, public file: File) {}
}

@Component({
  selector: 'app-image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent implements OnInit {

  selectedFile: ImageSnippet;
  @Output()
  imageUpload:EventEmitter<string> = new EventEmitter<string>();

  constructor() { }

  ngOnInit() {
  }

  processFile(imageInput: any) {
    const file: File = imageInput.files[0];
    const reader = new FileReader();

    reader.addEventListener('load', (event: any) => {

      this.selectedFile = new ImageSnippet(event.target.result, file);
      this.imageUpload.emit(event.target.result);
      // this.imageService.uploadImage(this.selectedFile.file).subscribe(
      //   (res) => {
      //
      //   },
      //   (err) => {
      //
      //   })
    });

    reader.readAsDataURL(file);
  }

}
