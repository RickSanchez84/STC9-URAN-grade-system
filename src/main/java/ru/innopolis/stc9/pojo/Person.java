package ru.innopolis.stc9.pojo;

import java.sql.Date;

public class Person {
    private long id;
    private String name;
    private Date birthday;
    private String email;
    private int role;

    public Person(long id, String name, Date birthday, String email, int role) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.role = role;
    }

    public Person(String name, Date birthday, String email, int role) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}