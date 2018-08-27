import { Component, OnInit } from '@angular/core';
import { AuthorService } from '../../common/services/author.service';
import { ListModel } from '../../common/models/ListModel';
import { Router } from '@angular/router';

@Component({
  selector: 'GED-edit-author',
  templateUrl: './edit-author.component.html',
  styleUrls: ['./edit-author.component.scss']
})
export class EditAuthorComponent implements OnInit {
  authorList: ListModel;

  author: any;

  constructor(private authorService: AuthorService, private router: Router) {
    authorService.getListValue().subscribe(ls => {
      this.authorList = new ListModel(ls);
    });
  }

  loadAuthor(id: number) {
    this.authorService.getAuthor(id).subscribe(r => (this.author = r));
  }

  save() {
    this.authorService.saveAuthor(this.author).subscribe(r => {
      this.router.navigateByUrl('/');
    });
  }

  ngOnInit() {}
}
