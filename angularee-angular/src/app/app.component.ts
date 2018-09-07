import { Component, ApplicationRef } from '@angular/core';
import { MenuItem } from 'primeng/components/common/menuitem';
import { GedErrorService } from './common/service/error.service';

@Component({
  selector: 'GED-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(public gedErrorService: GedErrorService, public ref: ApplicationRef) {}

  mitms: MenuItem[] = [{ label: 'Bücher', routerLink: ['books'] }, { label: 'Autoren', routerLink: ['author-edit'] }];
}
