package com.example.controller;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzsQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.ClazzsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clazzs")
@RestController
public class ClazzsController {

    @Autowired
    private ClazzsService clazzsService;

    @GetMapping
    public Result clazzsQueryList(ClazzsQueryParam clazzsQueryParam){
        PageResult<Clazz> pageResult= clazzsService.getClazzsList(clazzsQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{ids}")
    public Result deleteClazzs(@PathVariable List<Integer> ids){
        clazzsService.deleteClazzById(ids);
        return Result.success();
    }

    @PostMapping
    public Result addClazzs(@RequestBody Clazz clazz){
        clazzsService.addClazzs(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result queryClazzsById(@PathVariable Integer id){
        Clazz clazz=clazzsService.queryClazzsById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result updateClazzs(@RequestBody Clazz clazz){
        clazzsService.updateClazzs(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result listClazzs(){
        List<Clazz> clazzList = clazzsService.listClazzs();
        return Result.success(clazzList);
    }
}
