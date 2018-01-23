package com.themodernbit.projecthumane.User;

/**
 * Created by antho on 1/15/2018.
 */

public class UserClass {

    private String userName;
    private String userPhoneNumber;
    private double userProgress;
    private String userPreferredLanguage;

    public UserClass(){}
    public UserClass(String name, String phone, String Language){
        this.userName = name;
        this.userPhoneNumber = phone;
        this.userProgress = 0.0;
        this.userPreferredLanguage = Language;
    }

    public void setUserName(String UserName){
        this.userName = UserName;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserPhoneNumber(String phoneNumber){
        this.userPhoneNumber = phoneNumber;
    }

    public String getUserPhoneNumber(){
        return this.userPhoneNumber;
    }

    public void setUserProgress(double UserProgress){
        this.userProgress = UserProgress;
    }

    public double getUserProgress(){
        return this.userProgress;
    }

    public void setUserPreferredLanguage(String UserPreferredLanguage){
        this.userPreferredLanguage = UserPreferredLanguage;
    }

    public String getUserPreferredLanguage(){
        return this.userPreferredLanguage;
    }

}
