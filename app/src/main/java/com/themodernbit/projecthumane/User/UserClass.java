package com.themodernbit.projecthumane.User;

/**
 * Created by antho on 1/15/2018.
 */

public class UserClass {

    private String userName;
    private String userPhoneNumber;

    public UserClass(){}
    public UserClass(String name, String phone){
        this.userName = name;
        this.userPhoneNumber = phone;
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

}
