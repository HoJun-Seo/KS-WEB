package com.HoJun.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Interests {
    @Id
    private String name;

    public Interests(){}
    public Interests(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
