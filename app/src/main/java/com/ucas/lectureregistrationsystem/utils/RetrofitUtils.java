package com.ucas.lectureregistrationsystem.utils;

import com.ucas.lectureregistrationsystem.api.UserApi;
import com.ucas.lectureregistrationsystem.bean.BaseResponse;
import com.ucas.lectureregistrationsystem.retrofit.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitUtils {

    public static void login(String username, String password, final CallBack callBack) {

        RetrofitManager.getInstance().getRetrofit().create(UserApi.class).login(username, password).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                callBack.onSuccess(response);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callBack.onFail(t);
            }
        });
    }

    public static void register(String name, String phone, final CallBack callBack) {
        RetrofitManager.getInstance().getRetrofit().create(UserApi.class).register(name, phone, "cugb").enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                callBack.onSuccess(response);

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callBack.onFail(t);

            }
        });
    }


    public interface CallBack {
        void onSuccess(Response<BaseResponse> baseResponse);

        void onFail(Throwable t);
    }

}
