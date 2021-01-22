import { KulturnaPonudaService } from './../SERVICES/kulturnaPonuda.service';
import { Recenzija } from './../MODELS/recenzija';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.scss']
})
export class ReviewsComponent implements OnInit {

  totalSize:number;
  subList:Recenzija[] | undefined;
  pageSize: number;
  currentPage: number;
  id:number;

  constructor(
    private router:Router,
    private kulService:KulturnaPonudaService

  ) {
    this.pageSize= 2;
    this.currentPage =1;
    this.totalSize = 1;
    this.id=100;
   }

  ngOnInit(): void {
    this.kulService.getRecenzijePage(this.currentPage, this.pageSize,this.id).subscribe(
			res => {
				
				this.subList = res.body.content as Recenzija[];
				this.totalSize = Number(res.body.totalElements);
			}
		);
  }

  natrag():void{
    this.router.navigate(['']);
  }

  changePage(newPage: number) {
		this.kulService.getRecenzijePage(newPage - 1, this.pageSize,this.id).subscribe(
			res => {
				
				this.subList = res.body.content as Recenzija[];
				this.totalSize = Number(res.body.totalElements);
			}
		);
	}

}
