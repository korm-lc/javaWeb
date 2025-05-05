package com.example.service;

import com.example.pojo.JobOption;
import com.example.pojo.StudentCountOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption countEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    StudentCountOption studentCountData();
}
