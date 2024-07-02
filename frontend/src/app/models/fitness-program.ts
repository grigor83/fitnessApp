import { Category } from "./category";
import { Comment } from "./comment";
import { User } from "./user";

export class FitnessProgram {

    id! : number;
    programName : string;
    description : string;
    imagePath : string;
    category! : Category;
    location : string;
    duration : string;
    intensity : number;
    price : number;
    author : User | null;
    comments : Comment[];
    instructorName : string;
    instructorContact : string;

    constructor (name : string, description : string, imagePath : string, category : Category, location : string, duration : string,
                    intensity : number, price : number, instructorName : string, instructorContact : string, author : User | null) {
            this.programName = name;
            this.description = description;
            this.category = category;
            this.imagePath = imagePath;
            this.location = location;
            this.duration = duration;
            this.intensity = intensity;
            this.price = price;
            this.comments = [];
            this.instructorName = instructorName;
            this.instructorContact = instructorContact;
            this.author = author;
    }

}

export class FitnessProgramArray {
    content!: FitnessProgram[];
    totalElements!: number;
}