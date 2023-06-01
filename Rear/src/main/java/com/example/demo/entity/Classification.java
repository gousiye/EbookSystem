package com.example.demo.entity;

public class Classification {
    private int id = -1;
    private String name = "";

    public Classification(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Classification{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}