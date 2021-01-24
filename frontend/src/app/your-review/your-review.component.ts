import { ToastrService } from 'ngx-toastr';
import { Recenzija} from './../MODELS/recenzija';
import { RecenzijaService } from './../SERVICES/recenzija.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

class ImageSnippet {
  pending: boolean = false;
  status: string = 'init';
  constructor(public src: string, public file: File) {}
}

@Component({
  selector: 'app-your-review',
  templateUrl: './your-review.component.html',
  styleUrls: ['./your-review.component.scss']
})
export class YourReviewComponent implements OnInit {

  temp:string | null;
  id:number;
  recForm:FormGroup;

  oceneLista:number[]

  //selectedFile: ImageSnippet=new ImageSnippet("",new File([],""));

  selectedFile = <ImageSnippet>{};

  //recenzija:Recenzija= new Recenzija(0,0,"",0,0,new File([],""));

  recenzija = <Recenzija>{};

  

  constructor(
    private fBuilder: FormBuilder,
    private router:Router,
    private route:ActivatedRoute,
    private recService:RecenzijaService,
    private toastr: ToastrService
  ) {
    this.oceneLista = [1,2,3,4,5];
    this.temp=this.route.snapshot.paramMap.get('idKul');
    if(this.temp != null)
      this.id = Number.parseInt(this.temp);
    else
      this.id = 0;
    this.recForm = this.fBuilder.group({
      ocena:[-1,[Validators.required]],
      komentar:["",[Validators.required]]
      
    });
   }

  ngOnInit(): void {
  }

  natrag(){
    this.router.navigate(['/kulturna-ponuda-detaljno']);
  }

  private onSuccess() {
    this.selectedFile.pending = false;
    this.selectedFile.status = 'ok';
  }

  private onError() {
    this.selectedFile.pending = false;
    this.selectedFile.status = 'fail';
    this.selectedFile.src = '';
  }
  
  napravi(imageInput:any):void{
    this.recenzija.ocena=this.recForm.value["ocena"];
    this.recenzija.komentar = this.recForm.value["komentar"]; 
    
    this.recenzija.kulId = this.id;
    this.recenzija.redId = 1;
    
    //if(imageInput.files[0]==null){
      
      this.recService.create(this.recenzija).subscribe(
        data=>{
          this.toastr.success('Review successfuly made!');
          this.recForm.reset();
          this.router.navigate(['/kulturna-ponuda-detaljno']);
        },
        error=>{
          this.toastr.error("Review failed to be made!");

        }
      )
    /*}else{
      
      const file: File = imageInput.files[0];
      const reader = new FileReader();
      
      //console.log(imageInput.files[0].path)
      reader.addEventListener('load', (event: any) => {

        this.selectedFile = new ImageSnippet(event.target.result, file);

        this.selectedFile.pending = true;
        
        this.recenzija.foto=file;
        
        
        this.recService.create(this.recenzija).subscribe(
          data=>{
            this.toastr.success('Review successfuly made!');
            this.recForm.reset();
            this.router.navigate(['/kulturna-ponuda-detaljno']);
          },
          error=>{
            this.toastr.error("Review failed to be made!");
  
          }
        )
      });

      reader.readAsDataURL(file);
    }*/

  }

}


