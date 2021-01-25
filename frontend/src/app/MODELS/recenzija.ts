export class Recenzija{
    id:number;
    ocena:number;
    komentar:string;
    regId:number;
    kulId:number;
    foto:string;
    

    constructor(id:number,ocena:number,komentar:string,regId:number,kulId:number,foto:string){
        this.id=id;
        this.ocena = ocena;
        this.komentar=komentar;
        this.regId=regId;
        this.kulId=kulId;
        this.foto=foto;
        
    }
}