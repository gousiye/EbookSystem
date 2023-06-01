package com.example.demo.service;

import com.example.demo.entity.Ebook;
import com.example.demo.entity.EbookFile;
import com.example.demo.mapper.EbookFileMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EbookFileService {

    @Autowired
    private EbookFileMapper ebookFileMapper;

    // 服务器此时的运行路径
    // 通常是 path = request.getServletContext().getRealPath("xxx/");
    // final private String localPath = "D:/LYZ_For_Study/EmbedTomcat";

    @Value("${books.upload.path}")
    private String baseUploadPath;

    @Value("${books.resource.path}")
    private String baseResourcePath;

    @Value("${server.path}")
    private String serverPath;


    //存储文件
    private void SaveFile(MultipartFile file, String bookFileName) throws IOException {
        String fileName = file.getOriginalFilename(); //获取文件名和后缀名
        String suffix = fileName.substring(fileName.lastIndexOf("."));  //获取文件后缀名

        File dir = new File(baseUploadPath);
        if(!dir.exists()){   // 没有对应路径的文件夹
            dir.mkdir();    // 创建目录
        }
        File uploadPath = new File(baseUploadPath + bookFileName);
        // 存在文件是修改
        if(uploadPath.exists()) {
            FileUtils.copyInputStreamToFile(file.getInputStream(), uploadPath);
        }
        else{
            file.transferTo(uploadPath);   // 存储文件
        }
    }

    //存储数据库
    private String SaveDB(MultipartFile file, String bookId, String bookName){
        String fileName = file.getOriginalFilename(); //获取文件名和后缀名
        String suffix = fileName.substring(fileName.lastIndexOf("."));  //获取文件后缀名

        bookName = bookId + "(" + bookName + ")";

        String url =  serverPath + baseResourcePath +  bookName + suffix;
        long size = ( file.getSize() + 1023 ) / 1024;   //获取文件大小，单位KB，进一法


        Map<String, Object> map = new HashMap<>();
        map.put("book_id", bookId);
        map.put("file_name", bookName);
        map.put("suffix", suffix);
        map.put("url", url);
        map.put("size", size);

        Integer isExisted = ebookFileMapper.checkExistence(bookId, suffix);
        if(isExisted != 0){
            ebookFileMapper.update(bookId, suffix, size);
        }
        else{
            ebookFileMapper.insert(map);
        }
        return bookName + suffix;
    }

    private void DeleteFile(String path) {
        File file = new File(path);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    @Transactional
    public void Save(MultipartFile file, String id, String name) throws IOException {
        String fileName = this.SaveDB(file, id, name);
        this.SaveFile(file, fileName);

    }

    //删除对应的文件和数据库中的记录
    @Transactional
    public void DeleteFileAndDB(String bookId){
        // 获得存储的文件名
        EbookFile tmpFile = ebookFileMapper.SelectFileNameById(bookId);
        if(tmpFile == null) return;  //可能存在文件上传失败为空的情况
        String path = tmpFile.getFileName() + tmpFile.getSuffix();
        path = this.baseUploadPath + path;
        this.DeleteFile(path);
        ebookFileMapper.delete(bookId);
    }

    public String SelectByBookId(String bookId){
        return ebookFileMapper.SelectUrlByBookId(bookId);
    }


    //根据一本书的url获得对应的书名和作者
    public String GetNameAuthorByUrl(String url){
        String result = "文件";
        String bookId = ebookFileMapper.SelectBookIdByUrl(url);
        Ebook tmpEbook = ebookFileMapper.GetNameAuthor(bookId);
        result = tmpEbook.getBookName() + "(";
        result += tmpEbook.getAuthorName() + ")";
        result += '.';
        result += tmpEbook.getSuffix();
        return result;
    }

}
