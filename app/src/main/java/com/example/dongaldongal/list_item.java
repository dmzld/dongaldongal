package com.example.dongaldongal;

public class list_item {

    private int image_club;
    private String text_club;
    private String text_title;
    private String text_date;
    private String text_content;

    public int getImage_club() {
        return image_club;
    }

    public void setImage_club(int image_club) {
        this.image_club = image_club;
    }

    public String getText_club() {
        return text_club;
    }

    public void setText_club(String text_club) {
        this.text_club = text_club;
    }

    public String getText_title() {
        return text_title;
    }

    public void setText_title(String text_title) {
        this.text_title = text_title;
    }

    public String getText_date() {
        return text_date;
    }

    public void setText_date(String text_date) {
        this.text_date = text_date;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public list_item(int image_club, String text_club, String text_title, String text_date, String text_content) {
        this.image_club = image_club;
        this.text_club = text_club;
        this.text_title = text_title;
        this.text_date = text_date;
        this.text_content = text_content;
    }
}
