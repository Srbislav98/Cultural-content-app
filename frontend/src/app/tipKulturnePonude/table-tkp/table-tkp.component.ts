import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TipKulturnePonude } from 'src/app/MODELS/tipKulturnePonude';
import { KulturnaPonudaService } from 'src/app/SERVICES/kulturnaPonuda.service';

@Component({
  selector: 'app-table-tkp',
  templateUrl: './table-tkp.component.html',
  styleUrls: ['./table-tkp.component.scss']
})
export class TableTkpComponent implements OnInit {
	@Input() subscriptions: TipKulturnePonude[] | undefined;

	constructor(
    private toastr: ToastrService,
    private router:Router,
    private kulturnaponudaService: KulturnaPonudaService,
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => {
      return false;
    };
  }

  ngOnInit() {}
  
  Obrisi(sub: TipKulturnePonude): void {
    console.log(sub);
    console.log(sub.id);
    
    this.kulturnaponudaService.deleteTKP(sub.id).subscribe(
			res => {
        this.toastr.success("Succcessful deleted type of cultural offer.");
        //this.router.navigate(['']);
        this.router.navigate(['tip-kulturne-ponude']);
      },
      error => {
				this.toastr.error('You can not delete type if it is still a part of any existing cultural offer.');
			}
    );
  }
}
