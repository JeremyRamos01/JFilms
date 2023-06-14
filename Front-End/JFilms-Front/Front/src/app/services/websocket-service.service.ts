import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { io } from "socket.io-client";

import * as Rx from 'rxjs';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';


@Injectable({
  providedIn: 'root'
})
export class WebsocketServiceService {

  private socket!: WebSocket;


  

  constructor() { }


  connect(): Rx.Subject<MessageEvent>{
    this.socket = new WebSocket("ws://localhost:7070/api/updates/butacas");
    let observable = new Observable((observer:any) => {
      this.socket.onmessage = (event) => {
        console.log('event', event);
        observer.next(event.data);
      }
      this.socket.onerror = (event) => {
        console.log('event', event);
        observer.error(event);
      }
      this.socket.onclose = (event) => {
        console.log('event', event);
        observer.complete();
      }
    }
    );
    let observer = {
      next: (data: any) => {
        if(this.socket.readyState === WebSocket.OPEN){
          this.socket.send(JSON.stringify(data));
        }
      }
  
  }
  return Rx.Subject.create(observer, observable);
}

}
