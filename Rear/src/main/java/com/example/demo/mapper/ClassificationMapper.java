package com.example.demo.mapper;

import com.example.demo.entity.Classification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassificationMapper {
    // 获取所有的类名
    public List<Classification> GetAllNames();

    //根据名字检查类是否存在
    public Integer CheckClassExistence(@Param("class_name") String className);

    //是否存在类名和书的关系
    public Integer CheckMapExistence(@Param("classification_id")Integer classsId);

    //插入新的类别
    public Integer InsertClass(@Param("class_name")String className);

    //根据类名返回对应的类id
    public Integer SearchId(@Param("class_name")String className);

    //根据类的id删除指定类别
    public Integer DeleteById(@Param("id") Integer id);

}
