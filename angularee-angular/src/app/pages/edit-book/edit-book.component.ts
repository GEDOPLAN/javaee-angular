import { Component, OnInit } from '@angular/core';
import { Router, RouterStateSnapshot, ActivatedRouteSnapshot, ActivatedRoute } from '@angular/router';
import { BooksService, PublisherService, Book, AuthorService, ListValue } from '../../generated';
import { ListModel } from '../../common/models/ListModel';

@Component({
  selector: 'GED-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.scss']
})
export class EditBookComponent implements OnInit {
  book: Book;

  publisherList: ListModel;
  authorList: ListModel;

  constructor(
    private service: BooksService,
    route: ActivatedRoute,
    private router: Router,
    publisherService: PublisherService,
    authorService: AuthorService
  ) {
    service.getBook(route.snapshot.params.id).subscribe(r => {
      this.book = r;
      publisherService.getListValues1().subscribe(ls => {
        this.publisherList = new ListModel(ls, r.publisher);
      });
      authorService.getListValues().subscribe(ls => {
        this.authorList = new ListModel(ls, r.authors);
      });
    });
  }

  ngOnInit() {}

  save() {
    this.service.setBook(this.book.id, this.book).subscribe(r => {
      this.router.navigateByUrl('/books');
    });
  }
}
