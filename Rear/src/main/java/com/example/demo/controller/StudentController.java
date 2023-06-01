

package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/student")
    public List ShowAll(){
        List<Student> list = studentMapper.find();
        return list;
    }

    @PostMapping("/student")
    public String AddStudent(Student stu){
        int i = studentMapper.add(stu);
        if(i > 0)
            return "插入成功";
        else
            return "插入失败";
    }

}
