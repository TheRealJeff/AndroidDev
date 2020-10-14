package com.example.profileviewer;

//creates a profile object with the name, age and id parameters
public class Profile {

    protected String name;
    protected int age;
    protected int id;

    public Profile() {
        this.name = "";
        this.age = 0;
        this.id = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
