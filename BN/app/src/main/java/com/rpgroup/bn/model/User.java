package com.rpgroup.bn.model;

//用户类，与database的user对应
public class User {
  private String password;
  private String name;
  private int userId;

  public void setPassword(String password) {
    this.password = password;
  }
  public String getPassword() {
    return password;
  }

  public void setName(String name) {
    this.name = name;
  }
  public String getName() {
    return name;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
  public int getUserId() {
    return userId;
  }

}