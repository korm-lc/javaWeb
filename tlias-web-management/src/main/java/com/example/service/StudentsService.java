package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.Student;
import com.example.pojo.StudentsQueryParam;

import java.util.List;

public interface StudentsService {
    PageResult<Student> pageStudents(StudentsQueryParam studentsQueryParam);

    void deleteStudents(List<Integer> ids);

    void addStudents(Student student);

    Student queryStudentById(Integer id);

    void updateStudent(Student student);

    void updateStudentViolationScore(Integer id, Integer score);
}
