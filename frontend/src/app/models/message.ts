import { User } from "./user";

export class Message {

    porukaId!: number;
    posiljalac!: User|null;
    primalac!: User|null;
    tekst!: string[];
    datum! : string|null;

    constructor () {}
}
