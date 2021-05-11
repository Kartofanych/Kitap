package com.example.irekonelove4.DataRequest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularReq {

    @SerializedName("books")
    public List<BookForJson> books;

    public List<BookForJson> getPopBooks() {
        return books;
    }

    public void setPopBooks(List<BookForJson> books) {
        this.books = books;
    }
}
