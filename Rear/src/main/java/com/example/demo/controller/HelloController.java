
// Hello World for Spring Boot

package com.example.demo.controller;


import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.*;

import java.util.List;

// 相应请求的控制器
@RestController
public class HelloController {

    @Autowired
    private StudentMapper userMapper;

    // hello world
    @GetMapping("/hello")
    public String Hello(){
        return "Hello Spring Boot";
    }

    @RequestMapping(value = "/PostTest1", method = RequestMethod.POST)
    public String PostTest1(@RequestParam(value = "username", required = false)String name){
        System.out.println(name);
        return "This is a post request test";
    }

}
