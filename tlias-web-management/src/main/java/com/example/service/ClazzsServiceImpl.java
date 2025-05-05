package com.example.service;

import com.example.mapper.ClazzsMapper;
import com.example.pojo.Clazz;
import com.example.pojo.ClazzsQueryParam;
import com.example.pojo.EmpLog;
import com.example.pojo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzsServiceImpl implements ClazzsService{
    @Autowired
    private ClazzsMapper clazzsMapper;

    @Autowired
    private EmpLogService empLogService;
    @Override
    public PageResult<Clazz> getClazzsList(ClazzsQueryParam clazzsQueryParam) {
        List<Clazz> clazzList;
        try {
            PageHelper.startPage(clazzsQueryParam.getPage(),clazzsQueryParam.getPageSize());

            clazzList = clazzsMapper.getClazzsList(clazzsQueryParam);
        }finally {
            empLogService.saveEmpLog(new EmpLog(null,LocalDate.now(),"查询班级信息："+clazzsQueryParam));
        }

        clazzList.stream()
                .peek(clazz -> {
                    LocalDate today = LocalDate.now();

                    if (clazz.getBeginDate().isAfter(today)) {
                        clazz.setStatus("未开班"); // 课程开始时间在今天之后
                    } else if (!clazz.getEndDate().isBefore(today)) {
                        clazz.setStatus("已开班"); // 今天在开始和结束之间（含）
                    } else {
                        clazz.setStatus("已结课"); // 结束时间在今天之前
                    }

                }).toList();

        Page<Clazz> clazzPage = (Page<Clazz>) clazzList;
        return new PageResult<>(clazzPage.getTotal(),clazzPage.getResult());
    }

    @Transactional
    @Override
    public void deleteClazzById(List<Integer> ids) {
        try {
            clazzsMapper.deleteById(ids);
        }finally {
            empLogService.saveEmpLog(new EmpLog(null, LocalDate.now(), "删除班级信息：" + ids));
        }

    }

    @Transactional
    @Override
    public void addClazzs(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazz.setCreateDate(LocalDateTime.now());
        try {
            clazzsMapper.addClazzs(clazz);
        }finally {
            empLogService.saveEmpLog(new EmpLog(null, LocalDate.now(), "添加班级信息：" + clazz));
        }

    }

    @Override
    public Clazz queryClazzsById(Integer id) {
        return clazzsMapper.queryClazzsById(id);
    }

    @Transactional
    @Override
    public void updateClazzs(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        try {
            clazzsMapper.updateClazzs(clazz);
        }finally {
            empLogService.saveEmpLog(new EmpLog(null, LocalDate.now(), "修改班级信息：" + clazz));
        }

    }

    @Override
    public List<Clazz> listClazzs() {
         List<Clazz> clazzsList = clazzsMapper.listClazzs();

         clazzsList.stream().forEach(clazz -> {
             LocalDate today = LocalDate.now();

             if (clazz.getBeginDate().isAfter(today)) {
                 clazz.setStatus("未开班"); // 课程开始时间在今天之后
             } else if (!clazz.getEndDate().isBefore(today)) {
                 clazz.setStatus("已开班"); // 今天在开始和结束之间（含）
             } else {
                 clazz.setStatus("已结课"); // 结束时间在今天之前
             }
         });

         return clazzsList;
    }
}
