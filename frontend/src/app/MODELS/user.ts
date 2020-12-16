export class User{
    id:number;
    ime:string;
    prezime:string;
    korisnickoIme:string;
    email:string;
    lozinka:string;
    constructor(id:number,ime:string,prezime:string,korisnickoIme:string,email:string,lozinka:string){
        this.id=id;
        this.email=email;
        this.korisnickoIme=korisnickoIme;
        this.ime=ime;
        this.prezime=prezime;
        this.lozinka=lozinka;
    }
}