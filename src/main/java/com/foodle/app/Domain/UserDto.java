package com.foodle.app.Domain;

import java.util.Date;

public class UserDto {
    String name;
    String id;
    String password;
    String email;
    Date birthday;
   static int user_num = 0;

    UserDto() {}

    public UserDto(String id, String password) {
        this.id = id;
        this.password = password;
        this.user_num++;
    }

    public UserDto(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.user_num++;

    }

    public UserDto(String name, String id, String password, String email, Date birthday, int user_num) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.user_num++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getUser_num() {
        return user_num;
    }
}
