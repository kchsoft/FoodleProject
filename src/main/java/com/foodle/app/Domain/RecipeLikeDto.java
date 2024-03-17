package com.foodle.app.Domain;

public class RecipeLikeDto {
    private int bno;
    private String user_id;

    public RecipeLikeDto(){}

    public RecipeLikeDto(int bno, String user_id) {
        this.bno = bno;
        this.user_id = user_id;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "RecipeLikeDto{" +
                "bno=" + bno +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
