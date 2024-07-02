import { Attribute } from "./attribute";

export class Category {

    id! : number;
    categoryName : string;
    attribute! : Attribute;
    selected : boolean;

    constructor(categoryName : string){
        this.categoryName = categoryName;
        this.selected = false;
    }
}
