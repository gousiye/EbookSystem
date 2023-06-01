package com.example.demo.mapper;


import com.example.demo.entity.Ebook;
import com.example.demo.entity.EbookFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface EbookFileMapper {
    public Integer checkExistence(@Param("book_id")String bookID, @Param("suffix")String suffix);
    public Integer insert(Map map);
    public Integer update(@Param("book_id")String bookID, @Param("suffix")String suffix ,@Param("size") long Size);

    public EbookFile  SelectFileNameById(@Param("book_id")String bookId);
    public String SelectUrlByBookId(@Param("book_id")String bookId);
    public Integer delete(@Param("book_id")String bookId);

    public String SelectBookIdByUrl(@Param("url") String url);

    //根据id获得书的名字和作者名字
    public Ebook GetNameAuthor(@Param("book_id")String id);
}
