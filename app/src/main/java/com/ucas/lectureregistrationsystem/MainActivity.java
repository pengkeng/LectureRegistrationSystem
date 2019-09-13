package com.ucas.lectureregistrationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ucas.lectureregistrationsystem.bean.BaseResponse;
import com.ucas.lectureregistrationsystem.utils.RetrofitUtils;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mLoginTv, mRegisterTv;
    private EditText mUserNameTv, mPassWordTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        RetrofitUtils.login(username, password, new RetrofitUtils.CallBack() {
            @Override
            public void onSuccess(Response<BaseResponse> baseResponse) {
                Toast.makeText(getApplicationContext(), baseResponse.body().getMsg() + " ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(Throwable t) {
                Toast.makeText(getApplicationContext(), "登陆失败", Toast.LENGTH_LONG);
            }
        });

    }
}
