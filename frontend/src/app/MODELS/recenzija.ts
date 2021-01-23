export class Recenzija{
    id:number;
    ocena:number;
    komentar:string;
    redId:number;
    kulId:number;
    

    constructor(id:number,ocena:number,komentar:string,regId:number,kulId:number){
        this.id=id;
        this.ocena = ocena;
        this.komentar=komentar;
        this.redId=regId;
        this.kulId=kulId;
        
    }
}