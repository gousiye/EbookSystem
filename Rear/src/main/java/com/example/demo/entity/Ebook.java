package com.example.demo.entity;

import java.util.Arrays;
import java.util.List;


// 电子书类
public class Ebook {
    public static final int idLen = 12; //书id的长度
    private String id = "";
    private String bookName = "";
    private String authorName = "";
    // 一本书有多个类别
    private List<Classification> classifications;
    private String suffix = "";   //只有书名，作者，后缀名都相同才认为是一本书
    private String language = "";
    private int download = 0;
    private String introduction = "";
    private String publisher = "";


    public Ebook(List<String> classNames){
        for(String clasName: classNames){
             Classification classification = new Classification(clasName);
             this.classifications.add(classification);
        }
    }

    public Ebook(){}


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


    public List<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    @Override
    public String toString() {
        return "Ebook{" +
                "id='" + id + '\'' +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", classifications=" + classifications +
                ", suffix='" + suffix + '\'' +
                ", language='" + language + '\'' +
                ", download=" + download +
                ", introduction='" + introduction + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
