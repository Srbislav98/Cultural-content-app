export class Lokacija{
    id:number;
    nazivLokacije:string;
    geoDuzina:number;
    geoSirina:number;
    constructor(id:number,naziv:string,geoDuzina:number,geoSirina:number){
        this.id=id;
        this.nazivLokacije=naziv;
        this.geoDuzina=geoDuzina;
        this.geoSirina=geoSirina;
    }
}