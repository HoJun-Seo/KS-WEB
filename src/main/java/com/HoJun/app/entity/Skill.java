package com.HoJun.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Skill {
    @Id
    private String name;
    private Integer grade;

    public Skill(){}
    public Skill(String name, Integer grade){
        this.name = name;
        this.grade = grade;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getGrade() {
        return grade;
    }
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
