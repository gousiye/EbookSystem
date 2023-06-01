package com.example.demo.partEntity;

import com.example.demo.entity.Classification;

import java.util.List;

// 查询电子书所需要的类， 编号， 书名.后缀名， 类别， 作者， 下载量
public class QueryBook {
    public static final int idLen = 12; //书id的长度
    protected String id = "";
    protected String bookName = "";
    protected String authorName = "";
    // 一本书有多个类别，这里只要类别的名称，不用id
    protected List<String> classifications;
    protected String suffix = "";
    protected int download = 0;
    protected long size = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<String> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<String> classifications) {
        this.classifications = classifications;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }


    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "QueryBook{" +
                "id='" + id + '\'' +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", classifications=" + classifications +
                ", suffix='" + suffix + '\'' +
                ", download=" + download +
                ", size=" + size +
                '}';
    }
}
