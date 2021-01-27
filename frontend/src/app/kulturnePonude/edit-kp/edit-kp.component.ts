import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Lokacija } from 'src/app/MODELS/lokacija';
import { Subscription } from 'src/app/MODELS/subscription';
import { TipKulturnePonude } from 'src/app/MODELS/tipKulturnePonude';
import { FotografijaService } from 'src/app/SERVICES/fotografija.service';
import { KulturnaPonudaService } from 'src/app/SERVICES/kulturnaPonuda.service';

@Component({
  selector: 'app-edit-kp',
  templateUrl: './edit-kp.component.html',
  styleUrls: ['./edit-kp.component.scss']
})
export class EditKpComponent implements OnInit {
  regForm:FormGroup;
  sub= new Subscription(1,"","","",0,0);
  types:TipKulturnePonude[] | undefined;
  //type:TipKulturnePonude | undefined;
  locations:Lokacija[] | undefined;
  selectedType="";
  selectedLocation="";
  tip="";
  lokacija="";
  urlFoto="";
  fileToUpload:any=null;
  imgUrl:any;
  stariFotoid:number=0;

  constructor(
    private route:ActivatedRoute,
    private fBuilder: FormBuilder,
    private router: Router,
    private kulturnaPonudaService:KulturnaPonudaService,
    private fotoService:FotografijaService,
    private toastr: ToastrService
    ) {
      this.regForm = this.fBuilder.group({
        //tip: ["",[Validators.minLength(1)]],
        //lokacija: ["",[Validators.minLength(1)]],
        ime: ["", [Validators.minLength(1)]],
        address: ["",[Validators.minLength(1)]],
        description: ["",[Validators.minLength(1)]],
        location: ["", [Validators.minLength(1)]],
        type: ["", [Validators.minLength(1)]],
      });
     }

  ngOnInit(): void {
    this.kulturnaPonudaService.get(this.route.snapshot.paramMap.get('token')).subscribe(
      result => {
        //this.selectedLocation="Lokacija";
        //this.type="s";//new TipKulturnePonude(1,"dsd");
        this.sub = new Subscription(Number(result.id), result.naziv, result.adresa, result.opis, Number(result.idt), Number(result.idLokacije));
        /*
        this.kulService.getTip(this.kP.idt).subscribe(
          data =>{
            this.tipKP = new TipKulturnePonude(Number(data.id),data.naziv);
            
          },
          (error:any)=>{
            console.log(error);
          }
        );*/
      },
      (error:any)=>{
        console.log(error);
      });
      this.fotoService.getByCulturalId(this.route.snapshot.paramMap.get('token')).subscribe(
        result => {
          console.log(result);
          this.stariFotoid=result.id;
          console.log("wdwdwdwdwdwdwdwdw");
          console.log(this.stariFotoid);
          console.log(result.lokacijaFajl.split("assets/img/")[1]);
          console.log("AAAAAAAAAAA");
          console.log(result.lokacijaFajl.split("assets/img/")[1])
          this.urlFoto=result.lokacijaFajl.split("assets/img/")[1];
        },
        (error:any)=>{
          console.log(error);
        });
      this.kulturnaPonudaService.getLocations().subscribe(
        res => {
          console.log("mss");
          this.locations = res as Lokacija[];
          console.log(this.locations);
          const br1=this.sub.idLokacije;
          this.locations.forEach( (tajpi) =>{
              if(tajpi.id===br1){
                this.lokacija=tajpi.nazivLokacije;
              }
          });
        }
      );
      this.kulturnaPonudaService.getTypes().subscribe(
        res => {
          this.types = res as TipKulturnePonude[];
          const br2=this.sub.idt;
          this.types.forEach( (tajpi) =>{
              if(tajpi.id===br2){
                this.tip=tajpi.naziv;
              }
          });
        }
      );
    //this.route.snapshot.paramMap.get('token')
  }
  regIn(){
    //if(this.regForm.value["email"].length!=0) this.User.email=this.regForm.value["email"];
   // if(this.regForm.value["username"].length!=0) this.User.korisnickoIme=this.regForm.value["username"];
    if(this.regForm.value["ime"].length!=0) this.sub.naziv=this.regForm.value["ime"];
    if(this.regForm.value["address"].length!=0) this.sub.adresa=this.regForm.value["address"];
    if(this.regForm.value["description"].length!=0) this.sub.opis=this.regForm.value["description"];
    if(this.regForm.value["location"].length!=0) this.sub.idLokacije=this.regForm.value["location"];
    if(this.regForm.value["type"].length!=0) this.sub.idt=this.regForm.value["type"];
    this.kulturnaPonudaService.editKP(this.sub).subscribe(
      dataa=>{
        if(this.fileToUpload!=null){
          this.fotoService.createForCult(this.fileToUpload,dataa.id).subscribe(
            data=>{
              this.fotoService.delete(this.stariFotoid).subscribe(
                res=>{
                  
                }
              )
              this.toastr.success('Successful cultural offer editing.');
              this.router.navigate(['']);
            },
            error=>{
              this.toastr.error("Unsuccessful photo editing.");
            }
          )
        }else{
          this.toastr.success('Successful cultural offer editing.');
          this.router.navigate(['']);
        }
        //NE BI TREBALO OVAKO
        /*
        const auth: any = {};
        this.authenticationService.logout();
        auth.username = this.User.email;
        auth.password = this.User.lozinka;
        this.authenticationService.login(auth).subscribe(
          result => {
            //OVAKO DOBIJEM TOKEN
            localStorage.setItem('email',auth.username);
            localStorage.setItem('user', JSON.stringify(result));
            const item = localStorage.getItem('user');
            const decodedItem = JSON.parse(item!);
            localStorage.setItem('accessToken', decodedItem.accessToken);
            this.router.navigate(['profil']);
          },
          error => {
            this.toastr.error('Unsuccessful profile editing');
          });
          */
      },
      error=>{
        this.toastr.error("Unsuccessful cultural offer editing.");
      }
    )
  }
  handleFileInput(target:any){
    let files=target.files;
    this.fileToUpload=files.item(0);
    var reader = new FileReader();
    reader.readAsDataURL(files[0]);
    reader.onload=(_event)=>{
      this.imgUrl=reader.result;
      console.log("pegazela");
      console.log(this.imgUrl);
    }
  }
}
