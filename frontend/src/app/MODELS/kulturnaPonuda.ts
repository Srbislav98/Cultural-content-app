export class KulturnaPonuda{
    constructor(private id?:number,private naziv?:string,private adresa?:string,private opis?:string,private idt?:number,private idLokacije?:number){

    }

    get Id(){
        return this.id;
    }
    set Id(value){
        this.id = value;
    }
    get Naziv(){
        return this.naziv;
    }
    set Naziv(value){
        this.naziv = value;
    }
    get Adresa(){
        return this.adresa;
    }
    set Adresa(value){
        this.adresa = value;
    }
    get Opis(){
        return this.opis;
    }
    set Opis(value){
        this.opis = value;
    }
    get Idt(){
        return this.idt;
    }
    set Idt(value){
        this.idt = value;
    }
    get IdLokacije(){
        return this.idLokacije;
    }
    set IdLokacije(value){
        this.idLokacije = value;
    }
}