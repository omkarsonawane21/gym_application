package com.example.gym.gym;

public class Gym {
    private int gymid;
    private String gymname;
    private String address;
    private String photos;

    public Gym(int gymid, String gymname, String address, String photos) {
        this.gymid = gymid;
        this.gymname = gymname;
        this.address = address;
        this.photos = photos;
    }

    public int getGymid() {
        return gymid;
    }

    public void setGymid(int gymid) {
        this.gymid = gymid;
    }

    public String getGymname() {
        return gymname;
    }

    public void setGymname(String gymname) {
        this.gymname = gymname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
