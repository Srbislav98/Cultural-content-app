export class Recenzija{
    id:number;
    ocena:number;
    komentar:string;
    regId:number;
    kulId:number;
    //foto:File;
    

    constructor(id:number,ocena:number,komentar:string,regId:number,kulId:number){
        this.id=id;
        this.ocena = ocena;
        this.komentar=komentar;
        this.regId=regId;
        this.kulId=kulId;
        //this.foto=foto;
        
    }
}