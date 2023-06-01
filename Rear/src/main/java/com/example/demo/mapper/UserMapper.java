package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


// Mybatis with xml
@Mapper
public interface UserMapper {
    public List<User> QueryAll();
    public List<User> QueryOne(@Param("id")int id);
}
