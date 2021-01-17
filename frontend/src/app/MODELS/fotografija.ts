export class Fotografija{
    constructor(private _id?:number, private _lokacijaFajl?:string,private _kulId?:number,private _komId?:number){

    }
    get id(){
        return this._id;
    }
    set id(value){
        this._id = value;
    }
    get lokacijaFajl(){
        return this._lokacijaFajl;
    }
    set lokacijaFajl(value){
        this._lokacijaFajl = value;
    }
    get kulId(){
        return this._kulId;
    }
    set kulId(value){
        this._kulId = value;
    }
    get komId(){
        return this._komId;
    }
    set komId(value){
        this._komId = value;
    }
}