package com.example.service;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzsQueryParam;
import com.example.pojo.PageResult;

import java.util.List;

public interface ClazzsService {
    PageResult<Clazz> getClazzsList(ClazzsQueryParam clazzsQueryParam);

    void deleteClazzById(List<Integer> id);

    void addClazzs(Clazz clazz);

    Clazz queryClazzsById(Integer id);

    void updateClazzs(Clazz clazz);

    List<Clazz> listClazzs();
}
