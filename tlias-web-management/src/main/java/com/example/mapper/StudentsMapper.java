package com.example.mapper;

import com.example.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentsMapper {
    List<Student> studentList();

    void deleteStudents(List<Integer> ids);

    void addStudent(Student student);

    Student queryStudentById(Integer id);

    void updateStudent(Student student);

    @Update("update student set violation_score= violation_score+#{score}, violation_count=violation_count+1, " +
            "update_time=#{updateTime} where id=#{id}")
    void updateStudentViolationScore(Integer id, Integer score, LocalDateTime updateTime);

    List<Map<String, Object>> getStudentDegreeData();
}
