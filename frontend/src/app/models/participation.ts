import { FitnessProgram } from "./fitness-program";
import { User } from "./user";

export class Participation {

    user: User|null;
    fitnessProgram : FitnessProgram;
    paymentDate : string;
    paymentMethod : string;

    constructor(user : User | null, program : FitnessProgram, paymentDate : string | null, paymentMethod : string){
        this.user = user ?? null;
        this.fitnessProgram = program;
        this.paymentDate = paymentDate ?? '';
        this.paymentMethod = paymentMethod;
    }

}
