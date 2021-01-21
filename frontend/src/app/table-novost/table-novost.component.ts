import { Novost } from './../MODELS/novost';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-table-novost',
  templateUrl: './table-novost.component.html',
  styleUrls: ['./table-novost.component.scss']
})
export class TableNovostComponent implements OnInit {

  @Input() novosti: Novost[] | undefined;

	constructor() {}

	ngOnInit() {}

}
