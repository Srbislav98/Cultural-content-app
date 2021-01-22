export class Recenzija{
    id:number;
    ocena:number;
    komentar:string;
    redId:number;
    kulId:number;
    foto:File;

    constructor(id:number,ocena:number,komentar:string,regId:number,kulId:number,foto:File){
        this.id=id;
        this.ocena = ocena;
        this.komentar=komentar;
        this.redId=regId;
        this.kulId=kulId;
        this.foto=foto;
    }
}