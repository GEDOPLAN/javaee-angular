import { Component } from '@angular/core';
import { MenuItem } from 'primeng/components/common/menuitem';

@Component({
  selector: 'GED-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  mitms: MenuItem[] = [{ label: 'Bücher', routerLink: ['books'] }, { label: 'Autoren', routerLink: ['author-edit'] }];
}
