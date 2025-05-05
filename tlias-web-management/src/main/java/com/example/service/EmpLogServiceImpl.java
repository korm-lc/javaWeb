package com.example.service;

import com.example.mapper.EmpLogMapper;
import com.example.pojo.EmpLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class EmpLogServiceImpl implements EmpLogService{

    @Autowired
    private EmpLogMapper empLogMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)//开启新事务
    public void saveEmpLog(EmpLog empLog) {
        empLogMapper.saveEmpLog(empLog);
    }
}
