import { ProfileService } from 'src/app/SERVICES/profile.service';
import { User } from './../MODELS/user';
import { Novost } from './../MODELS/novost';
import { Subscription } from 'src/app/MODELS/subscription';
import { JwtHelperService } from '@auth0/angular-jwt';
import { TipKulturnePonude } from './../MODELS/tipKulturnePonude';
import { AuthenticationService } from './../SERVICES/authentication.service';
import { KulturnaPonuda } from './../MODELS/kulturnaPonuda';
import { KulturnaPonudaService } from './../SERVICES/kulturnaPonuda.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-kulturna-ponuda-detaljno',
  templateUrl: './kulturna-ponuda-detaljno.component.html',
  styleUrls: ['./kulturna-ponuda-detaljno.component.scss']
})
export class KulturnaPonudaDetaljnoComponent implements OnInit {

  
  kulForm:FormGroup;
  kP = new KulturnaPonuda(1,"",",","",1,1);
  tipKP = new TipKulturnePonude(1,"");
  uloga = "";
  prosecnaOcena = -1.0;
  id = 100;
  
  idUser = 0;

  totalSize:number;
  subList:Novost[] | undefined;
  pageSize: number;
  currentPage: number;

  daLiJe:Boolean = true;

  vecDao:Boolean;

  constructor(private fBuilder:FormBuilder,
    private router:Router,
    private kulService:KulturnaPonudaService,
    private korService:ProfileService
    ) {
      this.kulForm = this.fBuilder.group({
        id:[""]
      });
      this.pageSize= 2;
      this.currentPage =1;
      this.totalSize = 1;
      this.daLiJe = true;
      this.vecDao=false;
      
  }

  ngOnInit(): void {
    this.ucitajKulturnuPonudu();
    const item = localStorage.getItem('user');
    const jwt: JwtHelperService = new JwtHelperService();
    const decodedItem = JSON.parse(item!);
    const info = jwt.decodeToken(decodedItem.accessToken);
    this.uloga = info['uloga'];
    this.idUser = 1;
    console.log(this.idUser);

    this.kulService.getProsecnaOcena(this.id).subscribe(
      result =>{
        this.prosecnaOcena = result;
      }
    )
    this.kulService.getNovostiPage(this.currentPage,this.pageSize, this.id).subscribe(
			res => {
				//console.log(res);
				//console.log(res.body.totalPages);
				this.subList = res.body.content as Novost[];
				this.totalSize = Number(res.body.totalElements);
			}
    );
    this.daLiJeSubscribe();
    this.vecDaoReview();
    
  }

  vecDaoReview(){
    this.kulService.getVecPostojiReview(this.id, this.idUser).subscribe(
      result =>{
        
        this.vecDao = result;
      }
    )
  }

  daLiJeSubscribe(){
    this.kulService.getDaLiPostoji(this.id,this.idUser).subscribe(
      result =>{
        
        this.daLiJe = result;
        
      }
    )
    //return this.daLiJe;
  }
  
  //odgovor:Boolean = this.daLiJeSubscribe();
  
  subscribe():void{
    this.korService.addSub(this.id,this.idUser).subscribe(
      res=>{
        this.router.navigate(['kulturna-ponuda-detaljno']);
        this.daLiJe=true;
      }
    );
    
    //this.odgovor= true;
  }

  unsubscribe():void{
    this.korService.deleteSub2(this.id,this.idUser).subscribe(
      res =>{
        this.router.navigate(['kulturna-ponuda-detaljno']);
        this.daLiJe=false;
      }  
    );
    
    //this.odgovor = false;
  }

  natrag():void{
    this.router.navigate(['']);
  }

  recenzije():void{
    this.router.navigate(['/reviews/'+this.id.toString()]);
  }

  dajRecenziju():void{
    this.router.navigate(['/your-review/'+this.id.toString()]);
  }

  dodajVest():void{
    this.router.navigate(['/add-news/'+this.id.toString()])
  }

  changePage(newPage: number) {
		this.kulService.getNovostiPage(newPage - 1, this.pageSize,this.id).subscribe(
			res => {
				
				this.subList = res.body.content as Novost[];
				this.totalSize = Number(res.body.totalElements);
			}
		);
	}
  
  ucitajKulturnuPonudu(){
    this.kulService.get(this.id).subscribe(
      result => {
        
        this.kP = new KulturnaPonuda(Number(result.id), result.naziv, result.adresa, result.opis, Number(result.idt), Number(result.idLokacije));
        this.kulService.getTip(this.kP.idt).subscribe(
          data =>{
            this.tipKP = new TipKulturnePonude(Number(data.id),data.naziv);
            
          },
          (error:any)=>{
            console.log(error);
          }
        );
      },
      (error:any)=>{
        console.log(error);
      });
  }

  
  //test = this.kulturnaPonuda?.Naziv;
}
