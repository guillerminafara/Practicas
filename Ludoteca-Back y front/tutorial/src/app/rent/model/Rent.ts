import { Customer } from "src/app/customer/model/Customer";
import { Game } from "src/app/game/model/Game";

export class Rent {
    id: number;
    game: Game;
    customer: Customer;
    initialDate: String;
    endDate: String;
}