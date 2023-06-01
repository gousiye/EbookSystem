package com.example.demo.controller;


import com.example.demo.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ClassificationController {
    @Autowired
    private ClassificationService classificationService;

    // 返回数据库中有的类型和后缀名
    @GetMapping("/table/init/class")
    public Map<String, Object> GetAllNames(){
        return classificationService.GetAllNames();
    }
}
