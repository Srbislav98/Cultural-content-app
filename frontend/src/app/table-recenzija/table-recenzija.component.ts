import { Recenzija } from './../MODELS/recenzija';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-table-recenzija',
  templateUrl: './table-recenzija.component.html',
  styleUrls: ['./table-recenzija.component.scss']
})
export class TableRecenzijaComponent implements OnInit {

  @Input() recenzije: Recenzija[] | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
