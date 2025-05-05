package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    /**
     * 查询总记录数
     */
    //@Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    //public Long count();

    /**
     *分页查询
     */
    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id " +
    //        "order by e.update_time desc")
    //public List<Emp> list();

   //@Select("select e.*,d.name from emp e left join dept d on e.dept_id=d.id\n" +
    //       "                  where e.name like '%阮%' or e.gender=2 or e.entry_date between '2017-01-01' and '2020-01-01'\n" +
     //      "                  order by e.update_time desc;")
    List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    //Insert a new employee into the emp table
    @Insert("insert into emp (username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time)" +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp empAddInfo);

    void deleteByIds(List<Integer> ids);

    Emp select(Integer id);

    void updateById(Emp emp);

    List<Map<String, Object>> countJobData();

    List<Map<String, Object>> countGenderData();


    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getEmpByUserAndPass(Emp emp);

    @Select("select * from emp")
    List<Emp> listAll();
}
