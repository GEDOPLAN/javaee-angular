import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListBooksComponent } from './pages/list-books/list-books.component';
import { EditBookComponent } from './pages/edit-book/edit-book.component';
import { EditAuthorComponent } from './pages/edit-author/edit-author.component';
import { LoginComponent } from './pages/login/login.component';
import { SecurityGuard } from './common/system/SecurityGuard';

const routes: Routes = [
  { path: '', redirectTo: 'books', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'books', component: ListBooksComponent, canActivate: [SecurityGuard] },
  { path: 'book-edit/:id', component: EditBookComponent, canActivate: [SecurityGuard] },
  { path: 'author-edit', component: EditAuthorComponent, canActivate: [SecurityGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
