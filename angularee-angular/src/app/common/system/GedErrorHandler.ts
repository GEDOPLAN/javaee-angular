import { ErrorHandler, Injectable, ApplicationRef, Injector } from '@angular/core';
import { MessageService } from 'primeng/components/common/messageservice';

@Injectable()
export class GedErrorHandler implements ErrorHandler {
  constructor(private errorService: MessageService, private injector: Injector) {}

  handleError(error: any): void {
    this.errorService.add({
      summary: error.message,
      severity: 'error'
    });
    this.injector.get(ApplicationRef).tick(); //force ui to update
  }
}
