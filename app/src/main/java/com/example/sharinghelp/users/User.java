package com.example.sharinghelp.users;

import android.net.Uri;

public class User {
    private String Username;
    private String email;
    private String phoneNumber;
    private String password;
    private int contributions,helps;
    private Uri profileImage, coverImage;

    public Uri getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Uri profileImage) {
        this.profileImage = profileImage;
    }

    public Uri getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Uri coverImage) {
        this.coverImage = coverImage;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    public int getHelps() {
        return helps;
    }

    public void setHelps(int helps) {
        this.helps = helps;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    private String profilePic;

    public User(String username) {
        Username = username;
    }

    public User(String username, String email, String phoneNumber, String password) {
        Username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }



}
