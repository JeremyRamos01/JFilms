/* import { Component, OnInit } from '@angular/core';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';


@Component({
  selector: 'app-web-scoket',
  templateUrl: './web-scoket.component.html',
  styleUrls: ['./web-scoket.component.css']
})
export class WebScoketComponent implements OnInit{

  client: Client = new Client();
  constructor() { }


  ngOnInit(): void {
    this.client = new Client();
    this.client.webSocketFactory = () => {
      return new SockJS("http://localhost:7070/api/updates/reserva");
    }

    this.client.onConnect = (frame) => {
      console.log("Conectados: " + this.client.connected + " : " + frame);
      this.client.subscribe("/topic/updates", e => {
        console.log(e.body);
      });
    }

  }

}
 */