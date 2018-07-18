package com.example.dongaldongal;
public class list_club {

    private String numberText;
    private String nameText;
    private String divText;
    private String phoneText;
    private String levelText;
    private String locationText;

    public String getText_number() { return numberText; }

    public void setNumberText(String numberText) {this.numberText = numberText; }

    public String getText_locaton() { return locationText; }

    public void setLocationText(String locationText) {this.locationText = locationText; }

    public String getText_name() { return nameText; }

    public void setNameText(String nameText) {this.nameText = nameText; }

    public String getText_div() { return divText; }

    public void setDivText(String divText) { this.divText = divText; }

    public String getText_phone() { return phoneText; }

    public void setPhoneText(String phoneText) { this.phoneText = phoneText; }

    public String getText_level() { return levelText; }

    public void setLevelText(String levelText) { this.levelText = levelText; }


    public list_club(String numberText, String nameText, String phoneText, String locationText, String divText, String levelText) {
        this.numberText = numberText;
        this.locationText = locationText;
        this.nameText = nameText;
        this.divText = divText;
        this.phoneText = phoneText;
        this.levelText = levelText;
    }
}