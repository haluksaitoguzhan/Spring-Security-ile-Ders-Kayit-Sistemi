package com.staj.staj.model;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private String password;
    private String email;
    private String name;
    private String no;
    private List<Course> courses = new ArrayList<>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public User getUserFromDto(){
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setNo(no);
        user.setCourses(courses);
        return user;
    }
    
}