package com.example.irekonelove4.DataRequest;

import com.google.gson.annotations.SerializedName;

public class BookNotForJson {
    @SerializedName("name")
    public String name;
    @SerializedName("author")
    public String author;
    @SerializedName("briefly")
    public String briefly;
    @SerializedName("genre")
    public String genre;
    @SerializedName("language")
    public String language;
    @SerializedName("img")
    public String img;
    @SerializedName("views")
    public String views;

    public BookNotForJson(String name, String author, String briefly, String genre, String language, String img,String views) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBriefly() {
        return briefly;
    }

    public void setBriefly(String briefly) {
        this.briefly = briefly;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}
