package com.example.azuredragon.login.activity;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azuredragon.IPresenter;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.R2;
import com.example.azuredragon.http.contract.LoginContract;
import com.example.azuredragon.http.presenter.LoginPresenter;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:登录
 */
public class LoginActivity extends MBaseActivity implements LoginContract.View {

    @BindView(R2.id.text_username)
    MaterialEditText textUsername;

    @BindView(R2.id.text_password)
    MaterialEditText textPassword;

    @BindView(R2.id.tv_login)
    TextView mTvLogin;

    @BindView(R2.id.tv_register)
    TextView mTvRegister;

    private LoginPresenter presenter;
    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.login_layout);
    }

    @Override
    protected void initData() {
        presenter = new LoginPresenter(this,this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    //注册成功后将用户名放入textUsername中
                    String username = data.getStringExtra("username");
                    textUsername.setText(username);
                    Toast.makeText(getApplicationContext(), "注册成功，请登录", Toast.LENGTH_SHORT).show();

                    //设置register按钮为Success
//                    button_register.setProgress(100);
                }
                break;
        }
    }


//    public void onLoginResult(Msg msg) {
//        if (msg.getSuccess()) {
//            //设置登陆按钮为Success
//            button_login.setProgress(100);
//            Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
//
//            //封装用户信息后存入系统
//            User user = new User(textUsername.getText().toString(), textPassword.getText().toString());
//            user.setRememberChecked(rememberPassword.isChecked());
//            user.setAutoLoginChecked(autoLogin.isChecked());
//            loginPresenter.saveUserInfo(user);
//
//            //进入主界面
//            Intent intent = new Intent(LoginActivity.this, UserInfoActivity.class);
//            intent.putExtra("username", textUsername.getText().toString());
//            startActivity(intent);
//            finish();
//        } else {
//            //设置登陆按钮为Error，并显示错误信息
//            button_login.setProgress(-1);
//            button_login.setErrorText(msg.getMessage());
//            Toast.makeText(getApplicationContext(),msg.getMessage(),Toast.LENGTH_SHORT).show();
//        }
//    }

//    @Override
//    public void startLogin() {
//        //设置登陆按钮为Progress，并设置为无限转动
//        button_login.setIndeterminateProgressMode(true);
//        button_login.setProgress(1);
//    }

    @OnClick(R.id.tv_login)
    public void login() {
        presenter.getBookList();
        //如果登陆按钮状态为Error，则进行归位
//        if(mTvLogin.getProgress() == -1){
//            mTvLogin.setProgress(0);
//        }
//        //先进行表单检查
//        else if(textUsername.validate() && textPassword.validate()){
//            User user = new User(textUsername.getText().toString(), textPassword.getText().toString());
//            loginPresenter.login(user);
//        }
    }

    @OnClick(R.id.tv_register)
    public void register() {
        presenter.getLiveData();
//        //如果注册按钮状态为Success，则进行归位
//        if(button_register.getProgress() == 100){
//            button_register.setProgress(0);
//        }
//        else {
            //跳转注册界面
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(intent, 1);
//        }
    }

    @Override
    public void success(String content) {

    }

    @Override
    public void fail(String content) {

    }
//
//    @OnClick(R.id.remember_password)
//    public void remember_password(){
//        //如果没有勾选记住密码，则不能勾选自动登陆
//        if(autoLogin.isChecked() && !rememberPassword.isChecked()){
//            autoLogin.setChecked(false);
//        }
//    }
//
//    @OnClick(R.id.auto_login)
//    public void autoLogin(){
//        //如果勾选自动登陆，记住密码也应被勾选
//        if(autoLogin.isChecked()){
//            rememberPassword.setChecked(true);
//        }
//    }
}