
export class Novost{
    constructor(private id?:number,private naziv?:string,private opis?:string,private datum?:Date){

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
    get Opis(){
        return this.opis;
    }
    set Opis(value){
        this.opis = value;
    }
    get Datum(){
        return this.datum;
    }
    set Datum(value){
        this.datum = value;
    }

}