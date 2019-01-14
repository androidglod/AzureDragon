package com.example.azuredragon.business.seting;

import com.example.azuredragon.IPresenter;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.business.login.activity.http.LoginContract;

import butterknife.OnClick;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:关于我们
 */
public class AboutUsActivity extends MBaseActivity {

//    @BindView(R2.id.text_username)
//    MaterialEditText textUsername;
//
//    @BindView(R2.id.text_password)
//    MaterialEditText textPassword;
//
//    @BindView(R2.id.tv_login)
//    TextView mTvLogin;
//
//    @BindView(R2.id.tv_register)
//    TextView mTvRegister;

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_about_dragon);
    }

    @Override
    protected void initData() {
    }



    @OnClick(R.id.rl_check_update)
    public void login() {

    }

    @OnClick(R.id.rl_allin_score)
    public void register() {
    }


}