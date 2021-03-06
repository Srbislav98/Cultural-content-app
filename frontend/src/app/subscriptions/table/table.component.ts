import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'src/app/MODELS/subscription';
import { ProfileService } from 'src/app/SERVICES/profile.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {
  @Input() subscriptions: Subscription[] | undefined;
  id:number=0;

	constructor(
    private toastr: ToastrService,
    private router:Router,
    private profileService: ProfileService,
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => {
      return false;
    };
  }

  ngOnInit() {
    this.profileService.getId().subscribe(
			res => {
				this.id=res.id;
			}
		);
  }
  
  Obrisi(sub: Subscription): void {
    console.log(sub);
    console.log(sub.adresa);
    console.log(sub.id);
    this.profileService.getId().subscribe(
			res => {
        console.log("asdsmdsod");
        console.log(res);
        this.id=res;
        this.profileService.deleteSub2(sub.id,this.id).subscribe(
          res => {
            /*
            var lista:Subscription[];
            if(this.subscriptions){
              lista=this.subscriptions;
              this.subscriptions=[];
              lista.forEach( (element) => {
                if(element.id!=sub.id){
                  this.subscriptions?.push(element);
                }
              });
            }
            */
            this.toastr.success("Succcessful unsubscription.");
            //this.router.navigate(['']);
            this.router.navigate(['profil']);
          },
          error => {
            this.toastr.error('Unsuccessful unsubscription.');
          }
        );
			}
		);
    
  }
}
