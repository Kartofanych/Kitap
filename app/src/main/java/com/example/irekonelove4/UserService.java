package com.example.irekonelove4;

import com.example.irekonelove4.DataRequest.BookForJson;
import com.example.irekonelove4.DataRequest.FirstPrReq;
import com.example.irekonelove4.DataRequest.PopularReq;
import com.example.irekonelove4.DataRequest.UserForJson;
import com.example.irekonelove4.LoginRequest.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @GET("api/all.php")
    Call<FirstPrReq> allInfo();

    @POST("api/login.php")
    Call<UserForJson> login(@Body LoginResponse loginResponse);

    @GET("api/popular.php")
    Call<List<BookForJson>> popInfo();

}
