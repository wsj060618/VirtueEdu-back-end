package org.perswsj;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;

public class Demo {

    public static void main(String[] args) throws Exception {
        // 填写Endpoint
        String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
        // 从环境变量中获取访问凭证。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 填写Bucket名称
        String bucketName = "wsj-java-ai";
        // 填写Object完整路径
        String objectName = "exampledir/example.jpg";
        // 填写Bucket所在地域
        String region = "cn-shanghai";

        // 创建OSSClient实例。
        // 当OSSClient实例不再使用时，调用shutdown方法以释放资源。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);        
        OSS ossClient = OSSClientBuilder.create()
        .endpoint(endpoint)
        .credentialsProvider(credentialsProvider)
        .clientConfiguration(clientBuilderConfiguration)
        .region(region)               
        .build();

        try {
            // 创建文件
            File file = new File("D:\\32188\\Pictures\\其它图片\\csgo图片.jpg");
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(fileBytes));
            
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);            
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}