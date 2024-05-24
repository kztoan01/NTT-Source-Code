package com.ntt.nttsearchfoodservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Page<E> {
    private int page;
    private List<E> list;
}
