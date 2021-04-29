package com.example.irekonelove4.DataRequest;

import com.google.gson.annotations.SerializedName;
public class BookForJson {
    @SerializedName("book_id")
    public String bookId;
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
    @SerializedName("language_original")
    public String languageOriginal;
    @SerializedName("text")
    public String text;
    @SerializedName("img")
    public String img;
    @SerializedName("views")
    public String views;
    @SerializedName("user_author")
    public String userAuthor;
    @SerializedName("size_of_book(pages)")
    public String sizeOfBookPages;
    @SerializedName("tags")
    public String tags;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public String getLanguageOriginal() {
        return languageOriginal;
    }

    public void setLanguageOriginal(String languageOriginal) {
        this.languageOriginal = languageOriginal;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(String userAuthor) {
        this.userAuthor = userAuthor;
    }

    public String getSizeOfBookPages() {
        return sizeOfBookPages;
    }

    public void setSizeOfBookPages(String sizeOfBookPages) {
        this.sizeOfBookPages = sizeOfBookPages;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
