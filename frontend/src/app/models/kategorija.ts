import { Atribut } from "./atribut";

export class Kategorija {

    kategorijaId! : number;
    nazivKategorije : string;
    atribut! : Atribut;

    constructor(nazivKategorije : string){
        this.nazivKategorije = nazivKategorije;
    }
}
