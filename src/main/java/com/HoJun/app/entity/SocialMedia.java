package com.HoJun.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class SocialMedia {
    @Id
    private String github;
    private String facebook;

    public SocialMedia(){}
    public SocialMedia(String github, String facebook) {
        this.github = github;

        this.facebook = facebook;
    }
    public String getGithub() {
        return github;
    }
    public void setGithub(String github) {
        this.github = github;
    }
    public String getFacebook() {
        return facebook;
    }
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
