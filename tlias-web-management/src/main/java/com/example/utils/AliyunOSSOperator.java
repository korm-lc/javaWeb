package com.example.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AliyunOSSOperator {
    // 设置 OSS Endpoint 和 Bucket 名称
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    // 替换为 Bucket 区域
    @Value("${aliyun.oss.region}")
    private String region;

    public String updload(byte[] content,String originFileName) throws ClientException {
        // 设置文件存储路径
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")) + "/";
        // 生成新的文件名
        String newFileName = UUID.randomUUID()+ originFileName.substring(originFileName.lastIndexOf("."));
        // 设置文件名
        String objectName = dir + newFileName;
        // 从环境变量中获取访问凭证。运行本代码示例之前，请先配置环境变量
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建 OSSClient 实例
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        // 显式声明使用 V4 签名算法
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();
        // 上传文件
        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        }finally {
            // 关闭 OSSClient
            ossClient.shutdown();
        }
        // 返回文件的 URL
        return endpoint.split("//")[0]+"//"+bucketName+"."+endpoint.split("//")[1]+"/"+objectName;
    }



}
