package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzsQueryParam {
    private String name;
    private LocalDate begin;
    private LocalDate end;
    private Integer page=1;
    private Integer pageSize=10;
}
