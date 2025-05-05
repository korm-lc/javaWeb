package com.example.service;

import com.example.pojo.Dept;

import java.time.LocalDateTime;
import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteById(Integer id);

    void add(Dept dept);

    void update(Dept dept);

    Dept findById(Integer deptId);
}
