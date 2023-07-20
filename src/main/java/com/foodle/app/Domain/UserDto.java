package com.foodle.app.Domain;


import java.time.LocalDate;

public class UserDto {
    private String name;
    private String id;
    private String password;
    private String email;
    private LocalDate birthday;

    UserDto() {}

    public UserDto(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public UserDto(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;

    }

    public UserDto(String name, String id, String password, String email, LocalDate birthday) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return name.equals(userDto.getName()) &&
                id.equals(userDto.getId()) &&
                password.equals(userDto.getPassword()) &&
                email.equals(userDto.getEmail()) &&
                birthday.equals(userDto.getBirthday());
    }
}
