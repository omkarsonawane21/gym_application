package com.example.gym.user;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String mobile;
    private String email;
    private String aadhar;
    private String profile;
    private int gymid;
    private String uname;
    private String password;

    public int getid() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAadhar() {
        return aadhar;
    }

    public String getProfile() {
        return profile;
    }

    public int getGymid() {
        return gymid;
    }

    public String getUname() {
        return uname;
    }

    public String getPassword() {
        return password;
    }

    public void setid(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setGymid(int gymid) {
        this.gymid = gymid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String name, String mobile, String email, String aadhar, String profile, int gymid, String uname, String password) {
        setid(id);
        setName(name);
        setMobile(mobile);
        setEmail(email);
        setAadhar(aadhar);
        setProfile(profile);
        setGymid(gymid);
        setUname(uname);
        setPassword(password);
    }
}
