export class Recenzija{
    constructor(private id?:number, private ocena?:number, private komentar?:string, private regId?:number, private kulId?:number, private fotoLokacija?:string){

    }

    get Id(){
        return this.id;
    }
    set Id(value){
        this.id = value;
    }
    get Ocena(){
        return this.ocena;
    }
    set Ocena(value){
        this.ocena = value;
    }
    get Komentar(){
        return this.komentar;
    }
    set Komentar(value){
        this.komentar = value;
    }
    get RegId(){
        return this.regId;
    }
    set RegId(value){
        this.regId = value;
    }
    get KulId(){
        return this.kulId;
    }
    set KulId(value){
        this.kulId = value;
    }
    get FotoLokacija(){
        return this.fotoLokacija;
    }
    set FotoLokacija(value){
        this.fotoLokacija = value;
    }
}