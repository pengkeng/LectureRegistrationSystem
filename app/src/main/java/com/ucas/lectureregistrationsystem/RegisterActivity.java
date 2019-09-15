package com.ucas.lectureregistrationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity {

    private Handler mHandler;
    private String COUNTRY_CODE = "86";
    private EventHandler eventHandler;
    private EditText mPhoneEt;
    private EditText mCodeEt;
    private TextView mSend;
    private TextView mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        mHandler = new Handler() {
            @Override
            public void dispatchMessage(@NonNull Message msg) {
                super.dispatchMessage(msg);
            }
        };

    }

    private void init() {
        mPhoneEt = findViewById(R.id.register_phone_et);
        mCodeEt = findViewById(R.id.phone_verify_code_et);
        mSend = findViewById(R.id.register_send_tv);
        mNext = findViewById(R.id.register_next_tv);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(mPhoneEt.getText().toString());
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode(mPhoneEt.getText().toString(), mCodeEt.getText().toString());
            }
        });
    }

    private void sendMessage(String phoneNum) {
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandler.sendMessage(msg);

            }
        };
        //注册一个事件回调监听，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);
        SMSSDK.getVerificationCode(COUNTRY_CODE, phoneNum);

    }

    private void verifyCode(String phone, String code) {
        SMSSDK.submitVerificationCode(COUNTRY_CODE, phone, code);
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

}
