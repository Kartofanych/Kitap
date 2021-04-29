package com.example.irekonelove4.DataRequest;

import com.google.gson.annotations.SerializedName;

public class UserForJson {
    @SerializedName("user_id")
    public String userId;
    @SerializedName("name")
    public String name;
    @SerializedName("login")
    public String login;
    @SerializedName("sex")
    public String sex;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("date")
    public String date;
    @SerializedName("city")
    public String city;
    @SerializedName("quote")
    public String quote;
    @SerializedName("favourite")
    public String favourite;
    @SerializedName("recommend_genres")
    public String recommendGenres;
    @SerializedName("recommend_authors")
    public String recommendAuthors;
    @SerializedName("interview")
    public String interview;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public String getRecommendGenres() {
        return recommendGenres;
    }

    public void setRecommendGenres(String recommendGenres) {
        this.recommendGenres = recommendGenres;
    }

    public String getRecommendAuthors() {
        return recommendAuthors;
    }

    public void setRecommendAuthors(String recommendAuthors) {
        this.recommendAuthors = recommendAuthors;
    }

    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }
}
