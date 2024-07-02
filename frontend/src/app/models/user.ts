import { Category } from "./category";
import { Message } from "./message";

export class User {

    id!: number;
    name: string;
    city: string; 
    username: string;
    password: string;
    mail: string;
    avatar: string|null;
    cardNumber : string;
    verified : boolean;
    councelor : boolean;
    recievedMessages: Message[];

    constructor(name : string, city : string, username:string, password:string, mail : string, 
                cardNumber : string, avatar : string|null) {
        this.name = name;
        this.city = city;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.cardNumber = cardNumber;
        this.verified = false;
        this.councelor = false;
        this.avatar = avatar;
        this.recievedMessages = [];
    }
}
