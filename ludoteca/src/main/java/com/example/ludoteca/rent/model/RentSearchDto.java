package com.example.ludoteca.rent.model;

import com.example.ludoteca.common.pagination.PageableRequest;

public class RentSearchDto {
    private PageableRequest pageable;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }
}
