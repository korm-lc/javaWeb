package com.example.controller;

import com.example.anno.LogOperation;
import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @LogOperation
    @PostMapping
    public Result save(@RequestBody  Emp empAddInfo){
        log.info("新增数据信息：{}",empAddInfo);
        empService.save(empAddInfo);
        return Result.success();
    }

    @LogOperation
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除数据信息：id为{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping({"/{id}"})
    public Result select(@PathVariable Integer id){
        log.info("查询信息：id为{}",id);
        Emp emp = empService.select(id);
        return Result.success(emp);
    }

    @LogOperation
    @PutMapping()
    public Result update(@RequestBody Emp emp){
        log.info("更新信息：id为{}",emp.getId());
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }
}

