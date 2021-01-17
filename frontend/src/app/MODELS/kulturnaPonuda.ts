export class KulturnaPonuda{
    constructor(private _id?:number,private _naziv?:string,private _adresa?:string,private _opis?:string,private _idt?:number,private _idLokacije?:number){

    }

    get id(){
        return this._id;
    }
    set id(value){
        this._id = value;
    }
    get naziv(){
        return this._naziv;
    }
    set naziv(value){
        this._naziv = value;
    }
    get adresa(){
        return this._adresa;
    }
    set adresa(value){
        this._adresa = value;
    }
    get opis(){
        return this._opis;
    }
    set opis(value){
        this._opis = value;
    }
    get idt(){
        return this._idt;
    }
    set idt(value){
        this._idt = value;
    }
    get idLokacije(){
        return this._idLokacije;
    }
    set idLokacije(value){
        this._idLokacije = value;
    }
}