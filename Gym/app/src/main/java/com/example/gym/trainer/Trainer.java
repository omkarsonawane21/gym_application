package com.example.gym.trainer;

import com.example.gym.user.User;

public class Trainer extends User {
    private boolean verified;
    private String info;
    private String certificate;
    private String photos;

    public Trainer(int id, String name, String mobile, String email, String aadhar, String profile, int gymid, String uname, String password, boolean verified, String info, String certificate, String photos) {
        super(id, name, mobile, email, aadhar, profile, gymid, uname, password);
        setVerified(verified);
        setInfo(info);
        setCertificate(certificate);
        setPhotos(photos);
    }

    public boolean isVerified(boolean verified) {
        return this.verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
