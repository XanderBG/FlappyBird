package com.example.xander.fappybird;

/**
 * Created by xander on 14-11-15.
 */
public class UserData {
    private Integer score = 0;
    private String userName = "";
    private String email = "";
    private String university = "";

    public UserData() {
    }

    public UserData(Integer score, String userName,
                    String email, String university) {
        this.score = score;
        this.userName = userName;
        this.email = email;
        this.university = university;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUniversity() {

        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getScore() {

        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
