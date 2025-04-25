import { Customer } from "src/app/customer/model/Customer";
import { Game } from "src/app/game/model/Game";

export class Rent {
    id: number;
    // nameGame:string;
    // nameCustomer:string
    game: Game;
    customer: Customer;
    startDate: Date;
    endDate: Date;

    
}