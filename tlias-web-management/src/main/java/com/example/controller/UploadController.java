package com.example.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.pojo.Result;
import com.example.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    //上传文件
    public Result upload(MultipartFile file) throws IOException, ClientException {
        log.info("文件上传：{}",file);
        String url = aliyunOSSOperator.updload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传成功：{}",url);
        return Result.success(url);
    }
}
