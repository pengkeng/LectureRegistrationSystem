package com.ucas.lectureregistrationsystem.api;

import com.ucas.lectureregistrationsystem.bean.BaseResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Test/login")
    Call<BaseResponse> login(@Query("username") String username,@Query("password") String password);

    @POST("Register")
    Call<BaseResponse> register(@Query("name") String name, @Query("phone") String phone, @Query("companyId") String companyId);

}
