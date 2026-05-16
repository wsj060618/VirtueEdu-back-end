package org.perswsj.controller;


import lombok.extern.slf4j.Slf4j;
import org.perswsj.utils.AliyunOSSOperator;
import org.perswsj.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@RestController
public class UpdateController {
//    /**
//     * 本地存储
//     * @param fileName 文件名
//     * @param age 文件年龄
//     * @param file 文件
//     * @return 上传结果
//     */
//    @PostMapping("/upload")
//    public Result upload(String fileName, Integer age, MultipartFile file) throws IOException {
//        log.debug("upload");
//        if (file.isEmpty()) {
//            return Result.error("File cannot be empty");
//        }
//        // 生成唯一文件名
//        String radomstr = UUID.randomUUID().toString().replaceAll("-", "");
//        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        String filename =  radomstr + extension;
//
//        // 保存文件
//        file.transferTo(new File("D:/image/" + filename));
//        return Result.success();
//    }

    @Autowired
    private AliyunOSSOperator ossOperator;

    /**
     * 上传文件到OSS
     * @param fileName 文件名
     * @param age 文件年龄
     * @param file 文件
     * @return 上传结果
     */
    @PostMapping("/upload")
    public Result upload(String fileName, Integer age, MultipartFile file) throws Exception {
        log.debug("upload");
        if (file.isEmpty()) {
            return Result.error("File cannot be empty");
        }

        // 保存文件到字节数组
        byte[] bytes = file.getBytes();
        // 上传文件到OSS
        String url = ossOperator.upload(bytes, Objects.requireNonNull(file.getOriginalFilename()));
        log.info("upload url:{}", url);
        return Result.success(url);
    }
}
