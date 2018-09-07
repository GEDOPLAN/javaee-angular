import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { BooksService, Book } from '../../generated';

@Component({
  selector: 'GED-list-books',
  templateUrl: './list-books.component.html',
  styleUrls: ['./list-books.component.scss']
})
export class ListBooksComponent implements OnInit {
  books$: Observable<Book[]>;

  constructor(private bookService: BooksService, private router: Router) {
    this.books$ = bookService.getBooks();
  }

  ngOnInit() {}

  open(id: number) {
    this.router.navigateByUrl(`book-edit/${id}`);
  }
}
