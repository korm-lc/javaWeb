package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpQueryParam {
    private Integer page=1;
    private Integer pageSize=10;
    private String name;
    private Integer gender;
    private LocalDate begin;
    private LocalDate end;
}
