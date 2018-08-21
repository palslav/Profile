package com.pals.profile;

public class Users {
    int id;
    String userName;
    String userAddress;
    String userLicense;
    String userGender;
    byte[] userImage;

    public Users(){ }

    public Users(String name, String address, String lisence, String gender, byte[] image){
        this.userName = name;
        this.userAddress = address;
        this.userLicense = lisence;
        this.userImage = image;
        this.userGender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String user_name) {
        this.userName = user_name;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String user_address) {
        this.userAddress = user_address;
    }

    public String getUserLicense() {
        return userLicense;
    }

    public void setUserLicense(String user_license) {
        this.userLicense = user_license;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String user_gender) {
        this.userGender = user_gender;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }
}
