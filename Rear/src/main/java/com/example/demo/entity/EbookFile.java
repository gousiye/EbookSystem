package com.example.demo.entity;


import org.springframework.beans.factory.annotation.Value;

public class EbookFile {

    @Value("${books.upload.path}")
    private String baseUrl;

    private String bookId;
    private String fileName;
    private String suffix;
    private String url;
    private Long size;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "EbookFile{" +
                "baseUrl='" + baseUrl + '\'' +
                ", bookId='" + bookId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", suffix='" + suffix + '\'' +
                ", url='" + url + '\'' +
                ", size=" + size +
                '}';
    }
}
