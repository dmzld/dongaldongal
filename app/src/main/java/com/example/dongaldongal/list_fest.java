package com.example.dongaldongal;

public class list_fest {


    private int image_fest;
    private String fest_name;
    private String fest_club;
    private String fest_date;
    private String fest_describe;

    public int getImage_fest() {
        return image_fest;
    }

    public void setImage_fest(int image_fest) {
        this.image_fest = image_fest;
    }

    public String getFest_name() {
        return fest_name;
    }

    public void setFest_name(String fest_name) {
        this.fest_name = fest_name;
    }

    public String getFest_club() {
        return fest_club;
    }

    public void setFest_club(String fest_club) {
        this.fest_club = fest_club;
    }

    public String getFest_date() {
        return fest_date;
    }

    public void setFest_date(String fest_date) {
        this.fest_date = fest_date;
    }

    public String getFest_describe() {
        return fest_describe;
    }

    public void setFest_describe(String fest_describe) {
        this.fest_describe = fest_describe;
    }

    public list_fest(int image_fest, String fest_name, String fest_club, String fest_date, String fest_describe) {
        this.image_fest = image_fest;
        this.fest_name = fest_name;
        this.fest_club = fest_club;
        this.fest_date = fest_date;
        this.fest_describe = fest_describe;
    }
}
