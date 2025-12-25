package com.atguigu.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    //上传头像功能
    String upload(MultipartFile file);
}
