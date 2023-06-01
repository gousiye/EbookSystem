package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping()
    public Map<String, Object> FindAll(){
        List<User> list = userMapper.QueryAll();
        Map response = new HashMap<>();
        response.put("code", 20000);
        response.put("data",list);
        return response;
    }

//    @GetMapping("/{id}")
//    public Map<String, Object> FindOne(@PathVariable int id ){
//        List<User> list = userMapper.QueryOne(id);
//        Map response = new HashMap<>();
//        response.put("cost", 20000);
//        response.put("data", list);
//        return response;
//    }
    @PostMapping()
    public Map<String, Object> FineOne(int id){
        List<User> list = userMapper.QueryOne(id);
        System.out.println(id);
        Map<String ,Object> response = new HashMap<>();
        response.put("code", 20000);
        response.put("data", list);
        return response;
    }
}
