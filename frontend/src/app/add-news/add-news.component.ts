import { NovostService } from './../SERVICES/novost.service';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';
import { Novost } from './../MODELS/novost';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-news',
  templateUrl: './add-news.component.html',
  styleUrls: ['./add-news.component.scss']
})
export class AddNewsComponent implements OnInit {

  temp:string | null;
  id:number;

  novForm:FormGroup;
  novost= <Novost>{};

  constructor(
    private fBuilder: FormBuilder,
    private router: Router,
    private route:ActivatedRoute,
    private toastr: ToastrService,
    private novService:NovostService
  ) { 
    this.temp=this.route.snapshot.paramMap.get('idKul');
    if(this.temp != null)
      this.id = Number.parseInt(this.temp);
    else
      this.id = 0;
    this.novForm = this.fBuilder.group({
      naziv:["",[Validators.required]],
      opis:["",[Validators.required]]
    });
  }

  ngOnInit(): void {
  }

  natrag(){
    this.router.navigate(['/kulturna-ponuda-detaljno/'+this.id]);
  }

  napravi(){
    this.novost.naziv=this.novForm.value["naziv"];
    this.novost.opis = this.novForm.value["opis"];
    this.novost.datum = new Date();
    this.novService.create(this.novost, this.id).subscribe(
      data=>{
        this.toastr.success('Successfuly made news.');
          this.novForm.reset();
          this.router.navigate(['/kulturna-ponuda-detaljno/'+this.id.toString()]);
      },
      error=>{
        this.toastr.error("Fialed to make news.");
      }
    )
  }

}
