package com.example.service;

import com.example.mapper.ClazzsMapper;
import com.example.mapper.EmpMapper;
import com.example.mapper.StudentsMapper;
import com.example.pojo.JobOption;
import com.example.pojo.StudentCountOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentsMapper studentsMapper;

    @Autowired
    private ClazzsMapper clazzsMapper;
    @Override
    public JobOption countEmpJobData() {
        List<Map<String,Object>> empJobData =empMapper.countJobData();

        List<Object> posList = empJobData.stream().map(dataMap ->dataMap.get("pos")).toList();
        List<Object> numList = empJobData.stream().map(dataMap ->dataMap.get("num")).toList();

        return new JobOption(posList,numList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentsMapper.getStudentDegreeData();
    }

    @Override
    public StudentCountOption studentCountData() {
        List<Map<String,Object>> studentCountMapList = clazzsMapper.getStudentCountData();

        List<String> clazzList = studentCountMapList.stream().map(stringObjectMap ->
                stringObjectMap.get("name").toString()).toList();

        List<Integer> dataList = studentCountMapList.stream().map(stringObjectMap ->
                Integer.valueOf(stringObjectMap.get("data").toString())).toList();
        return new StudentCountOption(clazzList,dataList);
    }
}
