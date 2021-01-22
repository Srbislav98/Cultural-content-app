export class Fotografija{
    id:number;
    lokacijaFajl:string;
    foto:File;
    kulId:number;
    recId:number;    
    constructor( id:number,  lokacijaFajl:string, foto:File,kulId:number, recId:number){
        this.id=id;
        this.lokacijaFajl=lokacijaFajl;
        this.foto=foto;
        this.kulId=kulId;
        this.recId=recId;
    }
    
}