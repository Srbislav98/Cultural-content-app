import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subscription } from 'src/app/MODELS/subscription';
import { KulturnaPonudaService } from 'src/app/SERVICES/kulturnaPonuda.service';

@Component({
  selector: 'app-list-kp',
  templateUrl: './list-kp.component.html',
  styleUrls: ['./list-kp.component.scss']
})
export class ListKpComponent implements OnInit {
  regForm1:FormGroup;
  regForm2:FormGroup;
  pageSize: number;
  currentPage: number;
  totalSize: number;
  subList:Subscription[] | undefined;
  uloga: any;
  @Output() gotCulturalOffers = new EventEmitter<Array<number>>();
  trazi1:boolean = false;
  trazi2:boolean = false;
  trazim:string ="";

	constructor(
		private kulturnaPonudaService: KulturnaPonudaService,
		private fBuilder: FormBuilder,
	) {
		this.pageSize = 2;
		this.currentPage = 1;
		this.totalSize = 1;
		this.regForm1 = this.fBuilder.group({
			podatak: [""]
      });
    this.regForm2 = this.fBuilder.group({
        podatak: [""]
        });
      this.uloga = localStorage.getItem('uloga');
	}

	changePage(newPage: number) {
    if(!this.trazi1 && !this.trazi2) {
      this.kulturnaPonudaService.getByPage(newPage - 1).subscribe(
        res => {

          this.subList = res.content as Subscription[];
          this.totalSize = Number(res.totalElements);
          const locationIds = new Array<number>();
          for(let i=0;i<this.subList.length; i++) {
            locationIds.push(this.subList[i].idLokacije);
          }
          this.gotCulturalOffers.emit(locationIds);
        }
      );
    } else {
      if(this.trazim.length!=0 && this.trazi1){
        this.kulturnaPonudaService.searchAllByPage(this.trazim, newPage - 1, this.pageSize).subscribe(
        res=>{
          this.subList = res.body.content as Subscription[];
          //alert(this.subList.length);
          //alert(this.totalSize);
          this.totalSize = Number(res.body.totalElements);
          const locationIds = new Array<number>();
          for(let i=0;i<this.subList.length; i++) {
            locationIds.push(this.subList[i].idLokacije);
          }
          this.gotCulturalOffers.emit(locationIds);
        }
        );
      }else{
        this.kulturnaPonudaService.searchAllByLocationPage(this.trazim,this.currentPage - 1, this.pageSize).subscribe(
          res=>{
            this.subList = res.body.content as Subscription[];
            //alert(this.subList.length);
            //alert(this.totalSize);
            this.totalSize = Number(res.body.totalElements);
            const locationIds = new Array<number>();
            for(let i=0;i<this.subList.length; i++) {
              locationIds.push(this.subList[i].idLokacije);
            }
            this.gotCulturalOffers.emit(locationIds);
          }
          );
        }
      }

	}
	ngOnInit() {
    this.regForm1.value["podatak"] = "";
    this.regForm2.value["podatak"] = "";
    this.trazi1=false;
    this.trazi2=false;
    //this.trazim="";
		this.kulturnaPonudaService.getByPage(this.currentPage - 1).subscribe(
			res => {
				console.log(res.content);
				console.log(res.totalPages);
				this.subList = res.content as Subscription[];
        this.totalSize = Number(res.totalElements);
        const locationIds = new Array<number>();
        for(let i=0;i<this.subList.length; i++) {
          locationIds.push(this.subList[i].idLokacije);
        }
        this.gotCulturalOffers.emit(locationIds);
			}
		);
	}
	regIn(){
		if(this.regForm1.value["podatak"].length!=0){
      this.trazi1 = true;
      this.trazi2=false;
      this.trazim=this.regForm1.value["podatak"];
			this.kulturnaPonudaService.searchAllByPage(this.trazim,this.currentPage - 1, this.pageSize).subscribe(
			res=>{
				this.subList = res.body.content as Subscription[];
				//alert(this.subList.length);
				//alert(this.totalSize);
        this.totalSize = Number(res.body.totalElements);
        const locationIds = new Array<number>();
        for(let i=0;i<this.subList.length; i++) {
          locationIds.push(this.subList[i].idLokacije);
        }
        this.gotCulturalOffers.emit(locationIds);
			}
      );
		}
  }
  regIn2(){
		if(this.regForm2.value["podatak"].length!=0){
      this.trazi2 = true;
      this.trazi1=false;
      this.trazim=this.regForm2.value["podatak"];
			this.kulturnaPonudaService.searchAllByLocationPage(this.trazim,this.currentPage - 1, this.pageSize).subscribe(
			res=>{
				this.subList = res.body.content as Subscription[];
				//alert(this.subList.length);
				//alert(this.totalSize);
        this.totalSize = Number(res.body.totalElements);
        const locationIds = new Array<number>();
        for(let i=0;i<this.subList.length; i++) {
          locationIds.push(this.subList[i].idLokacije);
        }
        this.gotCulturalOffers.emit(locationIds);
			}
      );
		}
	}

}
