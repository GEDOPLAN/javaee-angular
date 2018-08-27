import { Component, OnInit } from '@angular/core';
import { BookService } from '../../common/services/book.service';
import { Router, RouterStateSnapshot, ActivatedRouteSnapshot, ActivatedRoute } from '@angular/router';
import { PublisherService } from '../../common/services/publisher.service';
import { AuthorService } from '../../common/services/author.service';
import { ValueTransformer } from '../../../../node_modules/@angular/compiler/src/util';
import { ListModel } from '../../common/models/ListModel';

@Component({
  selector: 'GED-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.scss']
})
export class EditBookComponent implements OnInit {
  book: any;

  publisherList: ListModel;
  authorList: ListModel;

  constructor(
    private service: BookService,
    route: ActivatedRoute,
    private router: Router,
    publisherService: PublisherService,
    authorService: AuthorService
  ) {
    service.getBook(route.snapshot.params.id).subscribe(r => {
      this.book = r;
      publisherService.getListValue().subscribe(ls => {
        this.publisherList = new ListModel(ls, r.publisher);
      });
      authorService.getListValue().subscribe(ls => {
        this.authorList = new ListModel(ls, r.authors);
      });
    });
  }

  ngOnInit() {}

  save() {
    this.service.saveBook(this.book).subscribe(r => {
      this.router.navigateByUrl('/books');
    });
  }
}
