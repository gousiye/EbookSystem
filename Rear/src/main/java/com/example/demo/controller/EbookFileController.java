package com.example.demo.controller;


import com.example.demo.config.CodeConfig;
import com.example.demo.service.EbookFileService;
import jodd.io.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


// 相应请求的控制器
@RestController
public class EbookFileController {

    @Value("${books.upload.path}")
    private String baseUploadPath;

    @Value("${server.path}")
    private String servePath;

    @Value("${books.resource.path}")
    private String baseResourcePath;

    @Autowired
    EbookFileService ebookFileService;

//  处理http://xxxxx/upload的post请求

    // 正式情况应该用Ebook实例， 这里只是方便测试
    // 懒得改了，反正这样也能用
    //上传为文件
    @PostMapping("/table/upload")
    public Map<String, Object> FileUpload(MultipartFile file, String id, String name, HttpServletRequest request) throws IOException{
        ebookFileService.Save(file, id, name);
        Map<String, Object> result = new HashMap<>();
        result.put("code", CodeConfig.OK);
        return result;
    }

    //下载文件
    @GetMapping("/table/{fileUrl}")
    public void Download(@PathVariable String fileUrl, HttpServletResponse response) throws  IOException{
        File uploadFile = new File(baseUploadPath + fileUrl);
        //下载的文件为书名(作者).suffix的组合
        fileUrl = this.servePath + this.baseResourcePath + fileUrl;
        String downloadName = ebookFileService.GetNameAuthorByUrl(fileUrl);
        ServletOutputStream os = response.getOutputStream();

        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(downloadName, "UTF-8"));
        response.setContentType("application/octet-stream");
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }



}
