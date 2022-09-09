package ru.kata.course.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;


@Component
public class User {

    @Generated("id")
    private Long id;
    private String name;
    private int age;
    private int weight;
    private String lastname;

    @Autowired
    public User() {}

    public  User(String name, int age, int weight, String lastname) {
        this.age = age;
        this.name = name;
        this.weight = weight;
        this.lastname = lastname;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
