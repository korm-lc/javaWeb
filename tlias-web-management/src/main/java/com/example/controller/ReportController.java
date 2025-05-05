package com.example.controller;


import com.example.pojo.JobOption;
import com.example.pojo.Result;
import com.example.pojo.StudentCountOption;
import com.example.service.EmpLogService;
import com.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    EmpLogService empLogService;
    @Autowired
    ReportService reportService;
    @GetMapping("/empJobData")
    public Result empJobData(){
        JobOption jobOption= reportService.countEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result empGenderData(){
        List<Map<String,Object>> empGenderList = reportService.getEmpGenderData();
        return  Result.success(empGenderList);
    }

    @GetMapping("/studentDegreeData")
    public Result studentDegreeData(){
        List<Map<String,Object>> studentDegreeData = reportService.getStudentDegreeData();
        return Result.success(studentDegreeData);
    }

    @GetMapping("/studentCountData")
    public Result studentCountData(){
        StudentCountOption studentCountOption = reportService.studentCountData();
        return Result.success(studentCountOption);
    }
}
