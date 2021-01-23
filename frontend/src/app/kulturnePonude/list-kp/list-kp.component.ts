import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subscription } from 'src/app/MODELS/subscription';
import { KulturnaPonudaService } from 'src/app/SERVICES/kulturnaPonuda.service';

@Component({
  selector: 'app-list-kp',
  templateUrl: './list-kp.component.html',
  styleUrls: ['./list-kp.component.scss']
})
export class ListKpComponent implements OnInit {
	regForm:FormGroup;
	pageSize: number;
	currentPage: number;
  totalSize: number;
  subList:Subscription[] | undefined;
  
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
		this.kulturnaPonudaService.getByPage(newPage - 1).subscribe(
			res => {
				
				this.subList = res.content as Subscription[];
				this.totalSize = Number(res.totalElements);
			}
		);
	}
	ngOnInit() {
		this.kulturnaPonudaService.getByPage(this.currentPage - 1).subscribe(
			res => {
				console.log(res.content);
				console.log(res.totalPages);
				this.subList = res.content as Subscription[];
				this.totalSize = Number(res.totalElements);
			}
		);
	}
	regIn(){
		if(this.regForm.value["podatak"].length!=0){
      
			this.kulturnaPonudaService.searchAllByPage(this.regForm.value["podatak"],this.currentPage - 1, this.pageSize).subscribe(
			res=>{
				this.subList = res.body.content as Subscription[];
				//alert(this.subList.length);
				//alert(this.totalSize);
				this.totalSize = Number(res.body.totalElements);
			}
      );
		}  
	}

}
