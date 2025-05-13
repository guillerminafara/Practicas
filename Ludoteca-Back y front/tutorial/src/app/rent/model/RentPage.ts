import { Pageable } from "src/app/core/model/page/Pageable";
import { Rent } from "./Rent";

export class RentPage {
    content: Rent[];
    pageable: Pageable;
    totalElements: number;
}