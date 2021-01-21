export class Fotografija{
    constructor(private id?:number, private lokacijaFajl?:string,private kulId?:number,private komId?:number){

    }
    get Id(){
        return this.id;
    }
    set Id(value){
        this.id = value;
    }
    get LokacijaFajl(){
        return this.lokacijaFajl;
    }
    set LokacijaFajl(value){
        this.lokacijaFajl = value;
    }
    get KulId(){
        return this.kulId;
    }
    set KulId(value){
        this.kulId = value;
    }
    get KomId(){
        return this.komId;
    }
    set KomId(value){
        this.komId = value;
    }
}