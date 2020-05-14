package com.HoJun.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class SocialMedia {
    @Id
    private String github;
    private String facebook;
    private String instagram;
    private String twitter;

    public SocialMedia(){
    }
    public SocialMedia(String github, String facebook, String instagram, String twitter){
        this.github = github;
        this.instagram = instagram;
        this.twitter = twitter;
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
    public String getInstagram() { return instagram; }
    public void setInstagram(String instagram) { this.instagram = instagram; }
    public String getTwitter() { return twitter; }
    public void setTwitter(String twitter) { this.twitter = twitter; }
}
