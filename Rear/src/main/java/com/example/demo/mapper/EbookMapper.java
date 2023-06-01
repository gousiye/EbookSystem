package com.example.demo.mapper;


import com.example.demo.entity.Ebook;
import com.example.demo.partEntity.FullBook;
import com.example.demo.partEntity.QueryBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EbookMapper {

    // 查询已有的电子书所有的后缀名
    public List<String> GetAllSuffix();

    // 三个条件分页查询电子书
    public List<QueryBook> Query(Map map);

    // 判断一本书是否模糊包含给定的分类
    public Integer CountCertainCondtion(@Param("class_name") String clssification, @Param("book_id") String id);

    // 获得所有符合条件的电子书，用于分页用
    public List<String> QueryAll(Map map);

    // 根据给定的书获得对应的类别名字
    public List<String> Book2Class(@Param("book_id")String id);

    // 根据给定的书获得对应的类的id
    public List<Integer> Book2ClassId(@Param("book_id")String id);

    // 判断新的id是否重复
    public Integer JudgeIDUnique(@Param("book_id") String bookId);

    //根据书名，作者和后缀名判断书籍是否重复
    public Integer CheckBook(@Param("book_name")String bookName,
                             @Param("author_name")String authorName);


    //构建书籍和类别的关系
    public Integer MapBookWithClassificaiton(@Param("book_id")String bookId, @Param("classification_id")Integer classId);

    //添加书籍
    public Integer InsertBook(Map map);


    //得到一本书除了类别的基本信息
    public FullBook GetABookInfo(@Param("book_id") String id);


    //删除book class的关联关系
    public Integer DeleteClassMap(@Param("book_id") String id);


    //删除ebook表中的记录
    public Integer DeleteBook(@Param("book_id")String id);

    //更新下载量
    public Integer UpdateDownload(@Param("book_id")String id);

    // 测试用
    public List<Map<String, Object>> All();
}
