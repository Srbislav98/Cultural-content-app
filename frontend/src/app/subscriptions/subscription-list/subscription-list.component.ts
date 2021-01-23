import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subscription } from 'src/app/MODELS/subscription';
import { ProfileService } from 'src/app/SERVICES/profile.service';

@Component({
  selector: 'app-subscription-list',
  templateUrl: './subscription-list.component.html',
  styleUrls: ['./subscription-list.component.scss']
})
export class SubscriptionListComponent implements OnInit {
	regForm:FormGroup;
	pageSize: number;
	currentPage: number;
  totalSize: number;
  subList:Subscription[] | undefined;
  
	constructor(
		private profileService: ProfileService,
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
		this.profileService.getAll(newPage - 1, this.pageSize).subscribe(
			res => {
				
				this.subList = res.body.content as Subscription[];
				this.totalSize = Number(res.body.totalElements);
			}
		);
	}
	ngOnInit() {
		this.profileService.getAll(this.currentPage - 1, this.pageSize).subscribe(
			res => {
				//console.log(res);
				//console.log(res.body.totalPages);
				this.subList = res.body.content as Subscription[];
				this.totalSize = Number(res.body.totalElements);
			}
		);
	}
	regIn(){
		if(this.regForm.value["podatak"].length!=0){
			this.profileService.searchAll(this.regForm.value["podatak"],this.currentPage - 1, this.pageSize).subscribe(
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
