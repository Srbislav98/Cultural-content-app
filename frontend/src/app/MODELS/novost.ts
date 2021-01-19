
export class Novost{
    constructor(private _id?:number,private _naziv?:string,private _opis?:string,private _datum?:Date){

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
    get opis(){
        return this._opis;
    }
    set opis(value){
        this._opis = value;
    }
    get datum(){
        return this._datum;
    }
    set datum(value){
        this._datum = value;
    }

}