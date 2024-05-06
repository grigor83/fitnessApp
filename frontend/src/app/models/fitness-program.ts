import { Kategorija } from "./kategorija";
import { Comment } from "./comment";
import { User } from "./user";

export class FitnessProgram {

    programId! : number;
    nazivPrograma : string;
    opis : string;
    nazivSlike : string;
    kategorija! : Kategorija;
    lokacija : string;
    trajanje : string;
    nivoTezine : number;
    cijena : number;
    autor : User | null;
    komentari : Comment[];
    imeInstruktora : string;
    kontakt : string;

    constructor (nazivPrograma : string, opis : string, nazivSlike : string, kategorija : Kategorija, lokacija : string, trajanje : string,
                    nivoTezine : number, cijena : number, imeInstruktora : string, kontakt : string, autor : User | null) {
            this.nazivPrograma = nazivPrograma;
            this.opis = opis;
            this.kategorija = kategorija;
            this.nazivSlike = nazivSlike;
            this.lokacija = lokacija;
            this.trajanje = trajanje;
            this.nivoTezine = nivoTezine;
            this.cijena = cijena;
            this.komentari = [];
            this.imeInstruktora = imeInstruktora;
            this.kontakt = kontakt;
            this.autor = autor;
    }

}

export class FitnessProgramArray {
    content!: FitnessProgram[];
    totalElements!: number;
}