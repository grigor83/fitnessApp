import { Kategorija } from "./kategorija";

export class User {

    korisnikId!: number;
    ime: string;
    grad: string; 
    korisnickoIme: string;
    lozinka: string;
    mejl: string;
    avatar: string|null;
    brojKartice : string;
    verifikovan : boolean;
    pretplata : Kategorija|null;

    constructor(ime : string, grad : string, korisnickoIme:string, lozinka:string, mejl : string, 
                brojKartice : string, avatar : string|null) {
        this.ime = ime;
        this.grad = grad;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.mejl = mejl;
        this.brojKartice = brojKartice;
        this.verifikovan = false;
        this.avatar = avatar;
        this.pretplata = null;
    }
}
