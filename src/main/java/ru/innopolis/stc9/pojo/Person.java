package ru.innopolis.stc9.pojo;

import java.sql.Date;

public class Person {
    private long id;
    private String name;
    private Date birthday;
    private String address;

    public Person(long id, String name, Date birthday, String address) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
    }

    public Person(String name, Date birthday, String address) {
        this.name = name;
        this.birthday = birthday;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
