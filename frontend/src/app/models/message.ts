import { User } from "./user";

export class Message {

    id!: number;
    sender!: User|null;
    reciever!: User|null;
    content!: string[];
    date! : string|null;

    constructor () {}
}
