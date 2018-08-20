package com.pals.profile;

public class Users {
    int id;
    String user_name;
    String user_address;
    String user_liscence;
    String user_image_path;
    String user_gender;

    public Users(){ }

    public Users(String name, String address, String lisence, String image_path, String gender){
        this.user_name = name;
        this.user_address = address;
        this.user_liscence = lisence;
        this.user_image_path = image_path;
        this.user_gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_liscence() {
        return user_liscence;
    }

    public void setUser_liscence(String user_liscence) {
        this.user_liscence = user_liscence;
    }

    public String getUser_image_path() {
        return user_image_path;
    }

    public void setUser_image_path(String user_image_path) {
        this.user_image_path = user_image_path;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }
}
