package com.example.irekonelove4.DataRequest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FirstPrReq {

    @SerializedName("users")
    public List<UserForJson> users;
    @SerializedName("books")
    public List<BookForJson> books;

    public List<UserForJson> getUsers() {
        return users;
    }

    public void setUsers(List<UserForJson> users) {
        this.users = users;
    }

    public List<BookForJson> getBooks() {
        return books;
    }

    public void setBooks(List<BookForJson> books) {
        this.books = books;
    }
}
