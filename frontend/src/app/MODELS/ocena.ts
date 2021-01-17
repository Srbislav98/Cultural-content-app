export class Ocena{
    constructor(private _id?:number,private _vrednost?:number, private _regId?:number, private _kulId?:number){

    }

    get id(){
        return this._id;
    }
    set id(value){
        this._id = value;
    }
    get vrednost(){
        return this._vrednost;
    }
    set vrednost(value){
        this._vrednost = value;
    }
    get regId(){
        return this._regId;
    }
    set regId(value){
        this._regId = value;
    }
    get kulId(){
        return this._kulId;
    }
    set kulId(value){
        this._kulId = value;
    }

}