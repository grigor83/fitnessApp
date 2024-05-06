import { FitnessProgram } from "./fitness-program";
import { User } from "./user";

export class Participation {

    korisnik: User|null;
    fitnessProgram : FitnessProgram;
    datum : string;
    nacinPlacanja : string;

    constructor(korisnik : User | null, program : FitnessProgram, datum : string | null, nacinPlacanja : string){
        this.korisnik = korisnik ?? null;
        this.fitnessProgram = program;
        this.datum = datum ?? '';
        this.nacinPlacanja = nacinPlacanja;
    }

}
