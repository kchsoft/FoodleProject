package com.foodle.app.Domain;

import java.time.LocalDateTime;

public class RecipePostDto {
    private int bno;
    private String title;
    private String content;
    private String writer_id;
    private int view_cnt;
    private int comment_cnt;
    private int like_cnt;
    private LocalDateTime register_date;
    private LocalDateTime up_date;


    public RecipePostDto(){}

    public RecipePostDto(String title, String content, String writer_id) {
        this.title = title;
        this.content = content;
        this.writer_id = writer_id;

        this.view_cnt = 0;
        this.comment_cnt = 0;
        this.like_cnt = 0;
        this.register_date = LocalDateTime.now();
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer_id;
    }

    public void setWriter(String writer_id) {
        this.writer_id = writer_id;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    public int getLike_cnt() {
        return like_cnt;
    }

    public void setLike_cnt(int like_cnt) {
        this.like_cnt = like_cnt;
    }

    public LocalDateTime getRegister_date() {
        return register_date;
    }

    public void setRegister_date(LocalDateTime register_date) {
        this.register_date = register_date;
    }

    public LocalDateTime getUp_date() {
        return up_date;
    }

    public void setUp_date(LocalDateTime up_date) {
        this.up_date = up_date;
    }

    @Override
    public String toString() {
        return "RecipePostDto{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer_id='" + writer_id + '\'' +
                ", view_cnt=" + view_cnt +
                ", comment_cnt=" + comment_cnt +
                ", like_cnt=" + like_cnt +
                ", register_date=" + register_date +
                ", up_date=" + up_date +
                '}';
    }
}
