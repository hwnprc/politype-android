package com.example.politype_android.model;

public class User {
    private String name; // 클래스 변수 (인스턴스 변수)
    private String politicalType;

    public User(String name){ // 매개변수 name
        this.name = name;  // this.name ('현재 객체의') name 변수 클래스 변수 = 매개변수
    }

    // 객체 안의 메서드들.
    public String getName(){
        return name;
    }
    public String getPoliticalType(){
        return politicalType;
    }

    public void setPoliticalType(String politicalType){
        this.politicalType = politicalType;
    }


}


