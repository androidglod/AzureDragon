package com.example.azuredragon.business.login.activity;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azuredragon.IPresenter;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.R2;
import com.example.azuredragon.business.login.activity.http.LoginContract;
import com.example.azuredragon.business.login.activity.http.LoginPresenter;
import com.example.azuredragon.cache.DbHelper;
import com.example.azuredragon.http.bean.LoginBean;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_SHORT;

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
        textUsername.setText("13522666927");
        textPassword.setText("111111");
//        textUsername.setText("ruffian");
//        textPassword.setText("EA8A706C4C34A168");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    //注册成功后将用户名放入textUsername中
                    String username = data.getStringExtra("username");
                    textUsername.setText(username);
                    Toast.makeText(getApplicationContext(), "注册成功，请登录", LENGTH_SHORT).show();

                    //设置register按钮为Success
//                    button_register.setProgress(100);
                }
                break;
        }
    }

    @OnClick(R.id.tv_login)
    public void login() {
        HashMap map = new HashMap();

        map.put("userName",textUsername.getText().toString());
        map.put("password",textPassword.getText().toString());
        presenter.goLogin(map);
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
//        presenter.getLiveData();
//        //如果注册按钮状态为Success，则进行归位
//        if(button_register.getProgress() == 100){
//            button_register.setProgress(0);
//        }
//        else {
            //跳转注册界面
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
//        }
    }

    @Override
    public void success(LoginBean mLoginBean) {
        //调用返回一个集合queryBuilder().where(UserDao.Properties.Name.eq("")).list()
//        List<LoginBean> mLoginBeans = DbHelper.getInstance().getmDaoSession().getLoginBeanDao().queryBuilder().where(LoginBeanDao.Properties.UserName.eq(mLoginBean.getUserName())).list();
//        if (mLoginBeans.size() == 0){
//            DbHelper.getInstance().getmDaoSession().getLoginBeanDao().insert(mLoginBean);
//        }
        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT);
    }

    @Override
    public void fail(String content) {
           Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT);
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