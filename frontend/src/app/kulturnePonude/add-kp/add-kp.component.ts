import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Fotografija } from 'src/app/MODELS/fotografija';
import { Lokacija } from 'src/app/MODELS/lokacija';
import { Subscription } from 'src/app/MODELS/subscription';
import { TipKulturnePonude } from 'src/app/MODELS/tipKulturnePonude';
import { AuthenticationService } from 'src/app/SERVICES/authentication.service';
import { FotografijaService } from 'src/app/SERVICES/fotografija.service';
import { KulturnaPonudaService } from 'src/app/SERVICES/kulturnaPonuda.service';
import { ProfileService } from 'src/app/SERVICES/profile.service';
import { RegistrationService } from 'src/app/SERVICES/registration.service';
@Component({
  selector: 'app-add-kp',
  templateUrl: './add-kp.component.html',
  styleUrls: ['./add-kp.component.scss']
})
export class AddKpComponent implements OnInit {
  sub= new Subscription(0,"","","",0,0);
  regForm:FormGroup;
  types:TipKulturnePonude[] | undefined;
  locations:Lokacija[] | undefined;
  selectedType="";
  selectedLocation="";
  fileToUpload:any=null;
  imgUrl:any;

  constructor(
    private fBuilder: FormBuilder,
    private router: Router,
    private kulturnaPonudaService:KulturnaPonudaService,
    private fotoService:FotografijaService,
    private toastr: ToastrService
    ) {
      this.regForm = this.fBuilder.group({
        ime: ["", [Validators.required,Validators.minLength(1)]],
        address: ["",[Validators.required,Validators.minLength(1)]],
        description: ["",[Validators.required,Validators.minLength(1)]],
        location: [undefined, [Validators.required]],
        type: [undefined, [Validators.required]],
      });
     }
    ngOnInit(): void {
      this.kulturnaPonudaService.getLocations().subscribe(
        res => {
          console.log("mss");
          this.locations = res as Lokacija[];
          console.log(this.locations);
        }
      );
      this.kulturnaPonudaService.getTypes().subscribe(
        res => {
          this.types = res as TipKulturnePonude[];
        }
      );
    }
    regIn(){
      this.sub.naziv=this.regForm.value["ime"];
      this.sub.adresa=this.regForm.value["address"];
      this.sub.opis=this.regForm.value["description"];
      this.sub.idLokacije=this.regForm.value["location"];
      this.sub.idt=this.regForm.value["type"];
      this.kulturnaPonudaService.addKP(this.sub).subscribe(
        dataa=>{
          this.fotoService.createForCult(this.fileToUpload,dataa.id).subscribe(
            data=>{
              this.toastr.success('Successful cultural offer adding.');
              this.router.navigate(['']);
            },
            error=>{
              this.toastr.error("Unsuccessful photo adding.");
            }
          )
        },
        error=>{
          this.toastr.error("Unsuccessful cultural offer adding.");
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
