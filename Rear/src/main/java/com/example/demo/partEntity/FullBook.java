package com.example.demo.partEntity;

import com.example.demo.entity.Classification;

import java.util.List;
import java.sql.Timestamp;

// 电子书的全部信息
public class FullBook extends QueryBook {
    public static final int idLen = 12; //书id的长度
    private String language = "";
    private String introduction = "";
    private String publisher = "";
    private Timestamp createTime = null;
    private Timestamp lastAlterTime = null;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastAlterTime() {
        return lastAlterTime;
    }

    public void setLastAlterTime(Timestamp lastAlterTime) {
        this.lastAlterTime = lastAlterTime;
    }

    @Override
    public String toString() {
        return "FullBook{" +
                "id='" + id + '\'' +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", classifications=" + classifications +
                ", suffix='" + suffix + '\'' +
                ", language='" + language + '\'' +
                ", download=" + download +
                ", introduction='" + introduction + '\'' +
                ", publisher='" + publisher + '\'' +
                ", size=" + size +
                '}';
    }
}
