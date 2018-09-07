import { ErrorHandler, Injectable, ApplicationRef, Injector } from '@angular/core';
import { MessageService } from 'primeng/components/common/messageservice';
import { GedErrorService } from '../service/error.service';

@Injectable()
export class GedErrorHandler implements ErrorHandler {
  constructor(private errorService: GedErrorService) {}

  handleError(error: any): void {
    this.errorService.addError(error.message);
  }
}
