import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'src/app/MODELS/subscription';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {
	@Input() subscriptions: Subscription[] | undefined;

	constructor() {}

	ngOnInit() {}
}
