import { Injectable, EventEmitter  } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmitterService {

  constructor() { }

  isLoginEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();

  
}
