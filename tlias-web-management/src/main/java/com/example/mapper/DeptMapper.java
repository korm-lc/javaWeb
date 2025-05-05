package com.example.mapper;

import com.example.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    public List<Dept> findAll();

    @Delete("delete from dept where id=#{id}")
    public void deleteById(Integer id);

    @Insert("insert into dept(name, create_time,update_time) values(#{name}, #{createTime}, #{updateTime})")
    public void add(Dept dept);

    @Update("update dept set name=#{name}, update_time=#{updateTime} where id=#{id}")
    public void update(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id=#{deptId}")
    public Dept findById(Integer deptId);

    @Select("select count(e.id) count from emp e left join dept d on e.dept_id = d.id where d.id= #{id}")
    int countEmpById(Integer id);
}