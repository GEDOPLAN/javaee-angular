import { Injectable, Injector, ApplicationRef } from '@angular/core';
import { Message } from 'primeng/components/common/message';

@Injectable({
  providedIn: 'root'
})
export class GedErrorService {
  private _messages: Message[] = [];

  constructor(private injector: Injector) {}

  addError(msg: string) {
    this.messages.push({
      severity: 'error',
      summary: msg
    });
    this.injector.get(ApplicationRef).tick();
  }

  get messages() {
    return this._messages;
  }

  set messages(messages: Message[]) {
    this._messages = messages;
  }
}
