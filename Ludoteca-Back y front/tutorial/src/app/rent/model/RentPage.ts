import { Pageable } from "src/app/core/model/page/Pageable";
import { Rent } from "./rent";


export class RentPage{
    content:Rent[];
    pageable:Pageable;
    totalElements:number;
}