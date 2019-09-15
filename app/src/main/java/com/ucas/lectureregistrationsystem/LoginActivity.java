package com.ucas.lectureregistrationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ucas.lectureregistrationsystem.bean.BaseResponse;
import com.ucas.lectureregistrationsystem.utils.RetrofitUtils;

import java.lang.ref.WeakReference;

import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView mLoginTv, mRegisterTv;
    private EditText mUserNameTv, mPassWordTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        mUserNameTv = findViewById(R.id.username_tv);
        mPassWordTv = findViewById(R.id.password_tv);
        mLoginTv = findViewById(R.id.login_tv);
        mRegisterTv = findViewById(R.id.register_tv);

        mLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void Login() {
        String username = mUserNameTv.getText().toString();
        String password = mPassWordTv.getText().toString();
        RetrofitUtils.login(username, password, new LoginCallBack(this));
    }


    private static class LoginCallBack implements RetrofitUtils.CallBack {

        private WeakReference<LoginActivity> mRef;

        LoginCallBack(LoginActivity loginActivity) {
            mRef = new WeakReference<>(loginActivity);
        }

        @Override
        public void onSuccess(Response<BaseResponse> baseResponse) {
            if (mRef != null) {
                if (mRef.get() != null) {
                    mRef.get().saveUser(baseResponse.body());
                    mRef.get().startActivity(new Intent(mRef.get(), MainActivity.class));
                    mRef.get().finish();
                }
            }
        }

        @Override
        public void onFail(Throwable t) {
        }
    }

    /**
     * 在本地存储用户的信息，记录登陆状态
     *
     * @param baseResponse
     */
    //todo 待开发
    private void saveUser(BaseResponse baseResponse) {

    }
}
