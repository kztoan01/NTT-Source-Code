package com.ntt.nttsearchfoodservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListPaging<E> {

    private int pageSize;
    private int pageTotal;
    private List<Page<E>> pages;

    public void setData(List<E> list) {
        pageTotal = (int) Math.ceil(((double) (list.size())) / ((double) (pageSize)));
        pages = new ArrayList<>();
        for (int i = 0; i < pageTotal; i++) {
            List<E> tmp = list.subList(i * pageSize, Math.min((i + 1) * pageSize, list.size()));
            Page<E> page = new Page<>(i + 1, tmp);
            pages.add(page);
        }
    }
}
