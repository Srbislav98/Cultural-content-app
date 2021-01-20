import { Component, OnInit, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import { UtilService } from '../SERVICES/util.service';

@Component({
	selector: 'app-pagination',
	templateUrl: './pagination.component.html',
	styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnInit, OnChanges {
	@Input() totalItems: number | undefined;
	@Input() pageSize: number | undefined;
	@Output() pageSelected: EventEmitter<number>;
	pages: number[] | undefined;
	activePage: number;

	constructor(
		private utilService: UtilService
	) {
		this.pageSelected = new EventEmitter();
		this.activePage = 1;
	}

	ngOnInit() {
    this.pages = [];
    if(this.totalItems && this.pageSize){
      for (let i = 1; i <= this.utilService.getNoPages(this.totalItems, this.pageSize); i++) {
        this.pages.push(i);
      }
    }
	}

	ngOnChanges(changes: any) {
		this.totalItems = changes.totalItems.currentValue;
    this.pages = [];
    if(this.totalItems && this.pageSize){
      for (let i = 1; i <= this.utilService.getNoPages(this.totalItems, this.pageSize); i++) {
        this.pages.push(i);
      }
    }
	}

	selected(newPage: number) {
    if(this.totalItems && this.pageSize){
      if (newPage >= 1 && newPage <= this.utilService.getNoPages(this.totalItems, this.pageSize)) {
        this.activePage = newPage;
        this.pageSelected.emit(this.activePage);
      }
    }
	}
}
