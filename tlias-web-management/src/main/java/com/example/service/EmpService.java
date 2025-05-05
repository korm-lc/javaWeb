package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.LoginInfo;
import com.example.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp empAddInfo);

    void delete(List<Integer> ids);

    Emp select(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);

    List<Emp> list();
}
