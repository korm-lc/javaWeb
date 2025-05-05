package com.example.service;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpLogMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.*;
import com.example.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService{
    @Autowired
    EmpExprMapper empExprMapper;
    @Autowired
    EmpMapper empMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //调用mapper接口,查询总记录数
        /*Long count = empMapper.count();
        //调用mapper接口，查询结果列表
        Integer start = (page - 1)*pageSize;
        List<Emp> empList = empMapper.list(page,pageSize);
        return new PageResult<Emp>(count,empList);*/
        //封装结果PageResult

        // 1. 开启分页
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
// 2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
// 3. 获取分页信息
        Page<Emp> p = (Page<Emp>)empList;

// 4. 返回分页结果
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Transactional//事务管理
    @Override
    public void save(Emp empAddInfo) {
        try {
            empAddInfo.setCreateTime(LocalDateTime.now());
            empAddInfo.setUpdateTime(LocalDateTime.now());
            empMapper.insert(empAddInfo);

            //保存员工工作经历
            List<EmpExpr> exprList = empAddInfo.getExprList();
            if (!exprList.isEmpty()){
                exprList.forEach(empExpr->{
                    empExpr.setEmpId(empAddInfo.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        }finally {
            EmpLog empLog = new EmpLog(null,LocalDate.now(),"新增员工："+empAddInfo);
            empLogService.saveEmpLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        try {
            empMapper.deleteByIds(ids);

            empExprMapper.deleteByEmpIds(ids);
        }finally {
            empLogService.saveEmpLog(new EmpLog(null ,LocalDate.now(),"删除员工以及员工经历信息:"+ids));
        }
    }

    @Override
    public Emp select(Integer id) {
        Emp emp;
        try {
            emp = empMapper.select(id);
        }finally {
            empLogService.saveEmpLog(new EmpLog(null,LocalDate.now(),"查询员工以及经历信息："+id));
        }
        return emp;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        try {
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.updateById(emp);

            empExprMapper.deleteByEmpIds(Collections.singletonList(emp.getId()));
            List<EmpExpr> empExprList = emp.getExprList();
            if(!empExprList.isEmpty()){
                empExprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(empExprList);
            }
        }finally {
            empLogService.saveEmpLog(new EmpLog(null,LocalDate.now(),"更新员工信息"+emp.getId()));
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.getEmpByUserAndPass(emp);
        if ( e!=null){
            log.info("登录成功,员工信息为{}",e);
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("id",e.getId());
            dataMap.put("username",e.getUsername());
            String token = JwtUtils.generateToken(dataMap);
            return new LoginInfo(e.getId(),e.getName(),e.getUsername(),token);
        }
        return null;
    }

    @Override
    public List<Emp> list() {
        return empMapper.listAll();
    }


}
