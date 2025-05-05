package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String address;
    private Integer clazzId;
    private LocalDateTime createTime;
    private Integer degree;
    private Integer gender;
    private Short isCollege;
    private LocalDate graduationDate;
    private String name;
    private String no;
    private String phone;
    private String idCard;
    private LocalDateTime updateTime;
    private Integer violationCount;
    private Integer violationScore;

    private String clazzName;
}
