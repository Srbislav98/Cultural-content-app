import { Component, Input, OnInit } from '@angular/core';
import { LokacijaNaMapi } from '../MODELS/LokacijaNaMapi';

@Component({
  selector: 'app-mapa',
  templateUrl: './mapa.component.html',
  styleUrls: ['./mapa.component.scss']
})
export class MapaComponent implements OnInit {

  @Input() lokacije: Array<LokacijaNaMapi> = [];

  constructor() { }

  ngOnInit(): void {
  }

}
