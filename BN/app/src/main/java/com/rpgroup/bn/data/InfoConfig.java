package com.rpgroup.bn.data;

public class InfoConfig {
    private static String userName;

    public static void setUserName(String s){
        userName=s;
    }

    public static String getUserName(){
        return userName;
    }
}
