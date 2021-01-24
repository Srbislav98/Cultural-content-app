import { FormGroup, FormBuilder } from '@angular/forms';
import { KulturnaPonudaService } from './../SERVICES/kulturnaPonuda.service';
import { Recenzija } from './../MODELS/recenzija';
import { Router, ActivatedRoute } from '@angular/router';
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
  temp:string | null;
  id:number;
  recForm:FormGroup;
  oceneLista:number[];
  temp2:number;

  constructor(
    private router:Router,
    private kulService:KulturnaPonudaService,
    private route:ActivatedRoute,
    private fBuilder: FormBuilder
  ) {
    this.pageSize= 2;
    this.currentPage =1;
    this.totalSize = 1;
    this.temp=this.route.snapshot.paramMap.get('idKul');
    if(this.temp != null)
      this.id = Number.parseInt(this.temp);
    else
      this.id = 0;
    this.recForm = this.fBuilder.group({
        ocena: [0]
        });
    this.oceneLista = [1,2,3,4,5];
    this.temp2=-1;
   }

  ngOnInit(): void {
    this.kulService.getRecenzijePage(this.currentPage-1, this.pageSize,this.id).subscribe(
			res => {
				
				this.subList = res.body.content as Recenzija[];
				this.totalSize = Number(res.body.totalElements);
			}
		);
  }

  natrag():void{
    this.router.navigate(['/kulturna-ponuda-detaljno']);
  }

  changePage(newPage: number) {
		this.kulService.getRecenzijePage(newPage - 1, this.pageSize,this.id).subscribe(
			res => {
				
				this.subList = res.body.content as Recenzija[];
				this.totalSize = Number(res.body.totalElements);
			}
		);
  }
  
  filter(){

      //this.temp2 = Number.parseInt()
			this.kulService.searchAllReviews(this.recForm.value["ocena"],this.currentPage - 1, this.pageSize, this.id).subscribe(
			res=>{
				this.subList = res.body.content as Recenzija[];
				//alert(this.subList.length);
				//alert(this.totalSize);
				this.totalSize = Number(res.body.totalElements);
			}
			);
		
  }

}
