package com.example.tasela.utils.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author jinmos
 * @date 2019-09-20 15:23
 */
@Data
public class PageResult implements Serializable {

    private Long recordsTotal;
    private Long recordsFiltered;
    private List<?> data;

    public PageResult (long recordsTotal, long recordsFiltered, List<?> data){
        super();
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }
}
