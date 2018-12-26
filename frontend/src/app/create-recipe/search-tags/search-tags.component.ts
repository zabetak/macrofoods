import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Tag } from '../../tag';
import { TagService } from '../../tag.service';
import { Observable, Subject } from 'rxjs';
import { of } from 'rxjs/observable/of';
import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

@Component({
  selector: 'app-search-tags',
  templateUrl: './search-tags.component.html',
  styleUrls: ['./search-tags.component.css']
})
export class SearchTagsComponent implements OnInit {
  tags: Observable<Tag[]>;
  @Output() selectedTag: EventEmitter<Tag> = new EventEmitter<Tag>();

  private searchTerms = new Subject<string>();
  searchValue: string;

  constructor(private tagService: TagService) { }

  ngOnInit() {
    this.tags = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.tagService.searchTags(term)),
    );
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  onSelect(tag: Tag): void {
    this.selectedTag.emit(tag);
    this.searchValue = '';
    this.searchTerms.next('');
  }

  newTag(tagName: string): void {
    let t: Tag = new Tag();
    t.description = tagName.toUpperCase();
    this.selectedTag.emit(t);
  }

}
