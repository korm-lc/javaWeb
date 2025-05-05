package com.example.service;

import com.example.mapper.StudentsMapper;
import com.example.pojo.PageResult;
import com.example.pojo.Student;
import com.example.pojo.StudentsQueryParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    StudentsMapper studentsMapper;
    @Override
    public PageResult<Student> pageStudents(StudentsQueryParam studentsQueryParam) {
        PageHelper.startPage(studentsQueryParam.getPage(),studentsQueryParam.getPageSize());

        List<Student> studentsList = studentsMapper.studentList();
        Page<Student> studentsPage = (Page<Student>) studentsList;

        return new PageResult<>(studentsPage.getTotal(),studentsPage.getResult());
    }

    @Override
    public void deleteStudents(List<Integer> ids) {
        studentsMapper.deleteStudents(ids);
    }

    @Override
    public void addStudents(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        student.setViolationCount(0);
        student.setViolationScore(0);
        studentsMapper.addStudent(student);
    }

    @Override
    public Student queryStudentById(Integer id) {
        return studentsMapper.queryStudentById(id);
    }

    @Override
    public void updateStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentsMapper.updateStudent(student);
    }

    @Override
    public void updateStudentViolationScore(Integer id, Integer score) {
        LocalDateTime updateTime = LocalDateTime.now();
        studentsMapper.updateStudentViolationScore(id,score,updateTime);
    }
}
