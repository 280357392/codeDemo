package com.huatec.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ImageView iv_showCode;
    private EditText et_phoneCode;
    private EditText et_phoneNum;
    private String realCode;
    private Button but_toSetCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //验证码输入框
        et_phoneCode = (EditText) findViewById(R.id.et_phoneCodes);


        //登录按钮
        but_toSetCode = (Button) findViewById(R.id.but_forgetpass_toSetCodes);
        but_toSetCode.setOnClickListener(this);


        //imageView用于显示验证码
        iv_showCode = (ImageView) findViewById(R.id.iv_showCode);
        //将验证码用图片的形式显示出来
        iv_showCode.setImageBitmap(Code.getInstance().createBitmap());
        iv_showCode.setOnClickListener(this);


        //获取验证码值
        realCode = Code.getInstance().getCode().toLowerCase();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //点击imageView（刷新验证码）
            case R.id.iv_showCode:
                iv_showCode.setImageBitmap(Code.getInstance().createBitmap());
                //获取最新验证码值
                realCode = Code.getInstance().getCode().toLowerCase();
                break;

            //点击"登录"按钮（比较验证码）
            case R.id.but_forgetpass_toSetCodes:
                //手动输入的验证码
                String phoneCode = et_phoneCode.getText().toString().toLowerCase();
                if (phoneCode.equals(realCode)) {//比较
                    Toast.makeText(MainActivity.this, phoneCode + "验证码正确", Toast.LENGTH_SHORT).show();
                } else {
                    String msg = "生成的验证码：" + realCode + "\n" + "输入的验证码:" + phoneCode;
                    Toast.makeText(MainActivity.this, "验证码错误:" + "\n" + msg, Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
