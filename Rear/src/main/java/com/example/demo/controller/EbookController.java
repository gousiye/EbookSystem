package com.example.demo.controller;

import com.example.demo.config.CodeConfig;
import com.example.demo.entity.Ebook;
import com.example.demo.mapper.EbookMapper;
import com.example.demo.service.EbookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
public class EbookController {
    @Autowired
    private EbookService ebookService;

    @GetMapping("table/ebook")
    public Map<String, Object> QueryBook(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam String bookName,
                                         @RequestParam  List<String> classification,
                                         @RequestParam String suffix){
        return ebookService.QueryBook(pageNum, pageSize, bookName, classification, suffix);
    }

    @GetMapping("/table/init/suffix")
    public Map<String, Object> GetAllSuffix(){
        return ebookService.GetAllSuffix();
    }

    @GetMapping("/table/ebook/all")
    public Map<String, Object> FindAll(){
        return ebookService.QueryAll();
    }

    @PostMapping("/table/add")
    public Map<String, Object>  AddBook(@RequestBody  Ebook ebook){
        return ebookService.AddBook(ebook);
    }
    @GetMapping("/table/info")
    public Map<String, Object> GetABookInfo(String id){
        return ebookService.GetABookInfo(id);
    }

    @GetMapping("/table/delete")
    public Map<String, Object> DeleteABook(String id){
        Map<String, Object> result = new HashMap<>();
        ebookService.DeleteABook(id);
        result.put("code",CodeConfig.OK);
        return result;
    }

    //批量删除
    @PostMapping("/table/multidelete")
    public Map<String, Object> MultiDelete(@RequestBody  List<String> ids){
        Map<String, Object> result = new HashMap<>();
        for(String iter : ids){
            ebookService.DeleteABook(iter);
        }
        result.put("code",CodeConfig.OK);
        return result;
    }

    @GetMapping("/table/bookUrl")
    public Map<String, Object> GetBookUrl(String id){
        return ebookService.GetBookUrl(id);
    }

    //更新下载量
    @GetMapping("/table/UpdateDownload")
    public Map<String, Object> UpdateDownload(String id){
        return ebookService.UpdateDownload(id);
    }

}


