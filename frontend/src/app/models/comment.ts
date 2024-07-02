import { FitnessProgram } from "./fitness-program";
import { User } from "./user";

export class Comment {
    id! : number;
    user! : User;
    date : string;
    content : string;
    program : FitnessProgram;

    constructor (program : FitnessProgram, date : string | null, content : string){
        this.program = program;
        this.date = date ?? '';
        this.content = content;
    }
}
