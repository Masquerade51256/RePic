package com.rpgroup.bn.model;

//存储当前登录用户的用户名
public class InfoConfig {
  private static String userName;
  public static void setUserName(String s) {
    userName = s;
  }
  public static String getUserName() {
    return userName;
  }
}
