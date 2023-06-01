package com.example.demo.mapper;

import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


// Mybatis with annotation
@Mapper
public interface StudentMapper {
    @Select("select * from Student")
    public List<Student> find();

    @Insert("insert into Student (id, name, age) values (#{id}, #{name}, #{age})")
    public int add(Student student);
}
