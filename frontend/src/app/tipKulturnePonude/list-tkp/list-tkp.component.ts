import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { TipKulturnePonude } from 'src/app/MODELS/tipKulturnePonude';
import { KulturnaPonudaService } from 'src/app/SERVICES/kulturnaPonuda.service';

@Component({
  selector: 'app-list-tkp',
  templateUrl: './list-tkp.component.html',
  styleUrls: ['./list-tkp.component.scss']
})
export class ListTkpComponent implements OnInit {
	regForm:FormGroup;
	pageSize: number;
	currentPage: number;
  totalSize: number;
  subList:TipKulturnePonude[] | undefined;
  
	constructor(
		private kulturnaPonudaService: KulturnaPonudaService,
		private fBuilder: FormBuilder,
	) {
		this.pageSize = 2;
		this.currentPage = 1;
		this.totalSize = 1;
		this.regForm = this.fBuilder.group({
			podatak: [""]
		  });
	}

	changePage(newPage: number) {
		this.kulturnaPonudaService.getTKPByPage(newPage - 1).subscribe(
			res => {
				
				this.subList = res.content as TipKulturnePonude[];
				this.totalSize = Number(res.totalElements);
			}
		);
	}
	ngOnInit() {
		this.kulturnaPonudaService.getTKPByPage(this.currentPage - 1).subscribe(
			res => {
				console.log(res.content);
				console.log(res.totalPages);
				this.subList = res.content as TipKulturnePonude[];
				this.totalSize = Number(res.totalElements);
			}
		);
	}

}
