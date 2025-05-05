package com.example.exception;

import com.example.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("程序出现错误："+e);
        return Result.error("出错了，请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateException(DuplicateKeyException e){
        log.error("程序出错了"+e);
        String msg = e.getMessage();
        int index= msg.indexOf("Duplicate Entry");
        String errMsg = msg.substring(index);
        String[] errMsgSplit = errMsg.split(" ");
        return Result.error(errMsgSplit[2]+"已经存在");
    }

    @ExceptionHandler
    public Result handleBusinessException(BusinessException e){
        log.error("删除出错"+e);
        return Result.error(e.getMessage());

    }
}
