package com.example.controller;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.Student;
import com.example.pojo.StudentsQueryParam;
import com.example.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    StudentsService studentsService;
    @GetMapping
    public Result pageStudents(StudentsQueryParam studentsQueryParam){
        PageResult<Student> studentPageResult = studentsService.pageStudents(studentsQueryParam);
        return Result.success(studentPageResult);
    }

    @DeleteMapping("/{ids}")
    public Result deleteStudents(@PathVariable List<Integer> ids){
        studentsService.deleteStudents(ids);
        return Result.success();
    }

    @PostMapping
    public Result addStudents(@RequestBody Student student){
        studentsService.addStudents(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result queryStudentById(@PathVariable Integer id){
        Student student = studentsService.queryStudentById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result updateStudent(@RequestBody Student student){
        studentsService.updateStudent(student);
        return Result.success();
    }
    @PutMapping("/violation/{id}/{score}")
    public Result updateStudentViolationScore(@PathVariable Integer id, @PathVariable Integer score){
        studentsService.updateStudentViolationScore(id,score);
        return Result.success();
    }
}
