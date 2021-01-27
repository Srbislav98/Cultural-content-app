import { Fotografija } from './../MODELS/fotografija';
import { Lokacija } from './../MODELS/lokacija';

import { ToastrService } from 'ngx-toastr';
import { ProfileService } from 'src/app/SERVICES/profile.service';
import { User } from './../MODELS/user';
import { Novost } from './../MODELS/novost';
import { Subscription } from 'src/app/MODELS/subscription';
import { JwtHelperService } from '@auth0/angular-jwt';
import { TipKulturnePonude } from './../MODELS/tipKulturnePonude';
import { AuthenticationService } from './../SERVICES/authentication.service';
import { KulturnaPonuda } from './../MODELS/kulturnaPonuda';
import { KulturnaPonudaService } from './../SERVICES/kulturnaPonuda.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { FotografijaService } from '../SERVICES/fotografija.service';

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
  id:number;
  temp:string | null;

  lokacija=<Lokacija>{};
  
  idUser=<number>{}

  totalSize:number;
  subList:Novost[] | undefined;
  pageSize: number;
  currentPage: number;

  daLiJe:Boolean = true;

  vecDao:Boolean;

  lokacijaSlike= <Fotografija>{};

  constructor(private fBuilder:FormBuilder,
    private router:Router,
    private kulService:KulturnaPonudaService,
    private fotoService:FotografijaService,
    private route:ActivatedRoute,
    private toastr: ToastrService,
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
      this.temp=this.route.snapshot.paramMap.get('idKul');
      if(this.temp != null)
        this.id = Number.parseInt(this.temp);
      else
        this.id = 0;

        this.korService.getId().subscribe(
          res=>{
            console.log("Ovde2!");
            console.log(res);
            this.idUser=res;
            
          }
        );
        this.lokacijaSlike.lokacijaFajl="assets/img/R6eb915d96d4c990aaf152a70c5fb54a9.png";
  }

  

  ngOnInit(): void {
    this.ucitajKulturnuPonudu();
    this.dajSlikuKul();
    this.kulService.getProsecnaOcena(this.id).subscribe(
      result =>{
        this.prosecnaOcena = result;
      }
    )
    this.kulService.getNovostiPage(this.currentPage-1,this.pageSize, this.id).subscribe(
			res => {
				//console.log(res);
				//console.log(res.body.totalPages);
				this.subList = res.body.content as Novost[];
        this.totalSize = Number(res.body.totalElements);
        console.log(res);
			}
    );
    this.dajLokaciju();
    

    const item = localStorage.getItem('user');
    const jwt: JwtHelperService = new JwtHelperService();
    const decodedItem = JSON.parse(item!);
    const info = jwt.decodeToken(decodedItem.accessToken);
    this.uloga = info['uloga'];
    
    
    console.log("Ovde!");
    console.log(this.idUser);

    
    this.korService.getId().subscribe(
      res=>{
        console.log("Ovde2!");
        console.log(res);
        this.idUser=res;
        this.daLiJeSubscribe();
        this.vecDaoReview();
      }
    );
    
    
  }

  dajSlikuKul(){
    console.log("aaaaa pato je"+this.temp)
    this.fotoService.getByCulturalId(this.temp).subscribe(
      result => {
        console.log(result);
        console.log(result.lokacijaFajl.split("assets/img/")[1]);
        console.log("AAAAAAAAAAA");
        console.log(result.lokacijaFajl.split("assets/img/")[1])
        this.lokacijaSlike.lokacijaFajl=result.lokacijaFajl.split("assets/img/")[1];
        console.log(this.lokacijaSlike.lokacijaFajl);
      },
      (error:any)=>{
        console.log(error);
      });
  }

  dajLokaciju(){
    this.kulService.getLokacija(this.id).subscribe(
      res=>{
        this.lokacija=res;
      }
    )
  }

  Obrisi(): void {
    

    this.kulService.deleteKP(this.id).subscribe(
			res => {
        this.toastr.success("Succcessful deleted cultural offer.");
        this.router.navigate(['']);
        //this.router.navigate(['kulturne-ponude']);
      },
      error => {
				this.toastr.error('Unsucccessful deleted cultural offer.');
			}
    );
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
        this.router.navigate(['kulturna-ponuda-detaljno/'+this.id]);
        this.daLiJe=true;
      }
    );
    
    //this.odgovor= true;
  }

  unsubscribe():void{
    this.korService.deleteSub2(this.id,this.idUser).subscribe(
      res =>{
        this.router.navigate(['kulturna-ponuda-detaljno/'+this.id]);
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
