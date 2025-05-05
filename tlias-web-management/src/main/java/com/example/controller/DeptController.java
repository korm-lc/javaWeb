package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查询所有部门
    //http://localhost:8080/depts
    @GetMapping
    public Result findAll(){
        List<Dept> results = deptService.findAll();
        return Result.success(results);
    }

    //删除部门
    @DeleteMapping
    public Result deleteById(Integer id){
        log.info("删除部门{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    //新增部门
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("增加部门{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据id查询部门信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId){
        log.info("根据部门嫩查询部门{}",deptId);
        Dept dept = deptService.findById(deptId);
        return Result.success(dept);
    }

    //根据id和名称修改部门信息
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("更新部门{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
