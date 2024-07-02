import { Category } from "./category";
import { User } from "./user";

export class Subscription {
    id! : number;
    category! : Category;
    user! : User;

    constructor() {

    }
}
