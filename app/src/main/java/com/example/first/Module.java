package com.example.first;

public class Module {
    private String name,age,phone;

    public Module(String name, String age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public Module() {
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }
}
