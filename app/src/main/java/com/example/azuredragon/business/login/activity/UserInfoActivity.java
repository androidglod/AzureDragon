package com.example.azuredragon.business.login.activity;

import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.azuredragon.IPresenter;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.business.login.validator.EmailValidator;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:
 */

public class UserInfoActivity extends MBaseActivity {

    @BindView(R.id.text_username)
    MaterialEditText textUsername;

    @BindView(R.id.text_email)
    MaterialEditText textEmail;

    @BindView(R.id.group_sex)
    RadioGroup groupSex;

    @BindView(R.id.tv_update)
    TextView mTvUpdate;

    @BindView(R.id.tv_logoff)
    TextView mTvLogoff;

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.userinfo_layout);
    }

    @Override
    protected void initData() {
        //添加表单验证器
        textEmail.addValidator(new EmailValidator());

        //修改默认样式
//        button_logoff.setProgress(-1);
        textUsername.setEnabled(false);

        //获取username，并且将其设置到textUsername中，
        String username = getIntent().getStringExtra("username");
        textUsername.setText(username);

    }

//    @Override
//    public void onQueryResult(Msg<User> msg) {
//        if(msg.getSuccess()){
//            //将用户数据渲染到控件上
//            User user = msg.getEntity();
//            textEmail.setText(user.getEmail());
//
//            if(user.getSex() == null){
//                groupSex.check(R.id.unknown);
//            }
//            else {
//                switch (user.getSex()){
//                    case "男":
//                        groupSex.check(R.id.man);
//                        break;
//                    case "女":
//                        groupSex.check(R.id.woman);
//                        break;
//                    case "未知":
//                        groupSex.check(R.id.unknown);
//                        break;
//                }
//            }
//        }
//        else {
//            Toast.makeText(getApplicationContext(),msg.getMessage(),Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onUpdateResult(Msg msg) {
//        //将更新按钮设置为Success，并显示信息
//        button_update.setProgress(100);
//        Toast.makeText(getApplicationContext(),msg.getMessage(),Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void startUpdate() {
//        //设置注册按钮为Progress，并设置为无限转动
//        button_update.setIndeterminateProgressMode(true);
//        button_update.setProgress(1);
//    }

//    @OnClick(R.id.button_update)
//    public void update() {
//        //如果更新按钮状态为Success，则进行归位
//        if(button_update.getProgress() == 100){
//            button_update.setProgress(0);
//        }
//        //先进行表单验证
//        else if(textEmail.getText().toString().equals("") || textEmail.validate()){
//            //包装表单数据，更新用户信息
//            User user = new User(textUsername.getText().toString());
//            user.setEmail(textEmail.getText().toString());
//
//            RadioButton radioButton = (RadioButton) findViewById(groupSex.getCheckedRadioButtonId());
//            user.setSex(radioButton.getText().toString());
//
//            userInfoPresenter.updateUserInfo(user);
//        }
//    }

    @OnClick(R.id.tv_logoff)
    public void logoff(){

        //登出后返回登录界面
        Intent intent = new Intent(UserInfoActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
