export class Subscription{
    id:number;
    naziv:string;
    adresa:string;
    opis:string;
    idt:number;
    idLokacije:number;
    constructor(id:number,naziv:string,adresa:string,opis:string,idt:number,idLokacije:number){
        this.id=id;
        this.naziv=naziv;
        this.adresa=adresa;
        this.opis=opis;
        this.idt=idt;
        this.idLokacije=idLokacije;

    }
}