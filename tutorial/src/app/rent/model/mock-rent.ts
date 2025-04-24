
import { RentPage } from "./RentPage";

export const RENT_DATA: RentPage={
    content: [
        {id: 1, nameGame:"Amapola",nameCustomer:"Paquito", startDate:'2025-04-23', endDate: '2025-04-27'},
        {id: 2, nameGame:"Juego2", nameCustomer:"Tito",startDate:'2025-04-20',endDate:'2025-04-27' },
        {id: 3, nameGame:"Juego6", nameCustomer:"CArmen", startDate: '2025-04-21', endDate:'2025-04-30'},
    ],
    pageable: {  
        pageSize:5,
        pageNumber:0,
        sort:[
            {property:"id", direction:"ASC"}
        ]},
    totalElements: 3
}