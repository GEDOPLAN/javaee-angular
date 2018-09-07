import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { CardModule } from 'primeng/card';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TabMenuModule } from 'primeng/tabmenu';
import { MessageModule } from 'primeng/message';
import { MessagesModule } from 'primeng/messages';
import { GrowlModule } from 'primeng/growl';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListBooksComponent } from './pages/list-books/list-books.component';
import { environment } from '../environments/environment.prod';
import { EditBookComponent } from './pages/edit-book/edit-book.component';
import { EditAuthorComponent } from './pages/edit-author/edit-author.component';
import { LoginComponent } from './pages/login/login.component';
import { SecurityInterceptor } from './common/system/SecurityInterceptor';
import { BASE_PATH, ApiModule } from './generated';
import { GedErrorHandler } from './common/system/GedErrorHandler';
import { MessageService } from 'primeng/components/common/messageservice';

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
    MessageModule,
    MessagesModule,
    GrowlModule,
    ApiModule
  ],
  providers: [
    MessageService,
    { provide: BASE_PATH, useValue: environment.baseurl },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: SecurityInterceptor,
      multi: true
    },
    {
      provide: ErrorHandler,
      useClass: GedErrorHandler
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
