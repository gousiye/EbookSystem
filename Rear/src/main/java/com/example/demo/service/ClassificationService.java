package com.example.demo.service;

import com.example.demo.config.CodeConfig;
import com.example.demo.entity.Classification;
import com.example.demo.mapper.ClassificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassificationService {

    @Autowired
    private ClassificationMapper classificationMapper;


    //获得所有的类别的名字
    public Map<String, Object> GetAllNames(){
        List<Classification> list = classificationMapper.GetAllNames();
        Map<String, Object> result = new HashMap<>();
        result.put("code", CodeConfig.OK);
        result.put("classes", list);
        return result;
    }

    //根据类别名字获得类别id，如果没有类别则插入
    public Integer GetAndCreateId(String className){
        Integer classId = 0;
        Integer isExist = classificationMapper.CheckClassExistence(className);
        // 如果不存在，需要先进行插入
        if(isExist == 0) {
            classificationMapper.InsertClass(className);
        }
        classId = classificationMapper.SearchId(className);
        return classId;
    }

    //删除一本书后，判断这本书对应的类别有无其它书籍对应，没有就删除
    @Transactional
    public void UpdateClass(List<Integer> affectedClasses){
        for(Integer iter: affectedClasses){
            Integer existence = classificationMapper.CheckMapExistence(iter);
            // 这个类是冗余的了
            if(existence == 0){
                classificationMapper.DeleteById(iter);
                // classificationMapper.UpdateId();
            }
        }
    }
}
