package com.example.demo.entity;

public class User {
    private String userName;
    private String password;

    // Java 不支持默认参数，只能通过重载的方式来代替
    public User(){
        userName = "username";
        password = "123456";
    }

    public User(String name, String word){
        this.userName = name;
        this.password = word;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
