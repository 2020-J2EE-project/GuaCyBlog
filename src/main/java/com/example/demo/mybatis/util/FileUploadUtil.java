package com.example.demo.mybatis.util;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUploadUtil {
    /**
     * 上传文件
     *
     * @param multipartFile
     * @return 文件存储路径
     */
    public static String upload(MultipartFile multipartFile) {
        // 文件存储位置，文件的目录要存在才行，可以先创建文件目录，然后进行存储
        String path= UUID.randomUUID().toString()+multipartFile.getOriginalFilename();
        File file1=new File("");
        String abspath=file1.getAbsolutePath();
        String filePath = abspath+"/GuaCyBlog-Vue/src/static/img/" + path;
        File file = new File(filePath);

        System.out.println(abspath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 文件存储
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}
