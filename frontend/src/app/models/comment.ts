import { FitnessProgram } from "./fitness-program";
import { User } from "./user";

export class Comment {
    komentarId! : number;
    korisnik! : User;
    datum : string;
    tekst : string;
    program : FitnessProgram;

    constructor (program : FitnessProgram, datum : string | null, tekst : string){
        this.program = program;
        this.datum = datum ?? '';
        this.tekst = tekst;
    }
}
