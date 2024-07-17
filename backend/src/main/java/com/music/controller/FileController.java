package com.music.controller;

import cn.hutool.core.io.FileUtil;
import com.music.common.lang.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    private static final String filePath = System.getProperty("user.dir") + "/file/";
    @RequiresAuthentication
    @ApiOperation(value = "上传图片接口，返回值为时间戳，图片存放在backend下的file文件夹下，命名为时间戳.jpg ")
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        synchronized (FileController.class) {
            String flag = System.currentTimeMillis() + "";
            String fileName = file.getOriginalFilename();
            try{
                if(!FileUtil.isDirectory(filePath)){
                    FileUtil.mkdir(filePath);
                }
                FileUtil.writeBytes(file.getBytes(),filePath+flag+".jpg");
            } catch (Exception e) {
                System.err.println(fileName+"文件上传失败");
            }
            return Result.succ(flag);
        }
    }
}
