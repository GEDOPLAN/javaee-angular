export * from './author.service';
import { AuthorService } from './author.service';
export * from './books.service';
import { BooksService } from './books.service';
export * from './login.service';
import { LoginService } from './login.service';
export * from './publisher.service';
import { PublisherService } from './publisher.service';
export const APIS = [AuthorService, BooksService, LoginService, PublisherService];
