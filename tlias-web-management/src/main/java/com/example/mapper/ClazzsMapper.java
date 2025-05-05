package com.example.mapper;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzsQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzsMapper {
    List<Clazz> getClazzsList(ClazzsQueryParam clazzsQueryParam);

    void deleteById(List<Integer> ids);

    void addClazzs(Clazz clazz);

    Clazz queryClazzsById(Integer id);

    void updateClazzs(Clazz clazz);

    List<Clazz> listClazzs();

    List<Map<String, Object>> getStudentCountData();
}
