import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { CardModule } from 'primeng/card';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TabMenuModule } from 'primeng/tabmenu';
import { MessageModule } from 'primeng/message';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListBooksComponent } from './pages/list-books/list-books.component';
import { environment } from '../environments/environment.prod';
import { BASEURL } from './app.tokens';
import { EditBookComponent } from './pages/edit-book/edit-book.component';
import { EditAuthorComponent } from './pages/edit-author/edit-author.component';
import { LoginComponent } from './pages/login/login.component';
import { UserService } from './common/services/user.service';
import { SecurityInterceptor } from './common/system/SecurityInterceptor';

@NgModule({
  declarations: [AppComponent, ListBooksComponent, EditBookComponent, EditAuthorComponent, LoginComponent],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    CardModule,
    AutoCompleteModule,
    ButtonModule,
    InputTextModule,
    TabMenuModule,
    MessageModule
  ],
  providers: [
    { provide: BASEURL, useValue: environment.baseurl },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: SecurityInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
