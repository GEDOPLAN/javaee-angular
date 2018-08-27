import { Component, OnInit } from '@angular/core';
import { BookService } from '../../common/services/book.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'GED-list-books',
  templateUrl: './list-books.component.html',
  styleUrls: ['./list-books.component.scss']
})
export class ListBooksComponent implements OnInit {
  books$: Observable<any[]>;

  constructor(private bookService: BookService, private router: Router) {
    this.books$ = bookService.getAllBooks();
  }

  ngOnInit() {}

  open(id: number) {
    this.router.navigateByUrl(`book-edit/${id}`);
  }
}
