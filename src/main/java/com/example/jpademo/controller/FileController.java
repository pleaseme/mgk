package com.example.jpademo.controller;

import com.example.jpademo.common.FtpFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
@RequestMapping(value = "/testfile")
public class FileController {

    @RequestMapping(value="/ftpuploadimg", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile file,
                                          HttpServletRequest request) throws IOException {

        String fileName = file.getOriginalFilename();
        InputStream inputStream=file.getInputStream();
        String filePath=null;



        Boolean flag= FtpFileUtil.uploadFile(fileName,inputStream);
        if(flag==true){
            System.out.println("ftp上传成功！");
            filePath=fileName;
        }


        return  "https://lbke.skgn.com/"+filePath;  //该路径图片名称，前端框架可用ngnix指定的路径+filePath,即可访问到ngnix图片服务器中的图片
    }


}
