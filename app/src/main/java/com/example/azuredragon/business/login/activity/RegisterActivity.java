package com.example.azuredragon.business.login.activity;

import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.azuredragon.IPresenter;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.business.login.activity.http.LoginPresenter;
import com.example.azuredragon.business.login.activity.http.RegisterContract;
import com.example.azuredragon.business.login.activity.http.RegisterPresenter;
import com.example.azuredragon.business.login.validator.EmailValidator;
import com.example.azuredragon.business.login.validator.EmptyValidator;
import com.example.azuredragon.business.login.validator.LengthValidator;
import com.example.azuredragon.business.login.validator.PasswordValidator;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:注册
 */
public class RegisterActivity extends MBaseActivity  implements RegisterContract.View {

    @BindView(R.id.text_username)
    public MaterialEditText textUsername;

    @BindView(R.id.text_password)
    MaterialEditText textPassword;

    @BindView(R.id.text_password_retry)
    MaterialEditText textPasswordRetry;

    @BindView(R.id.text_email)
    MaterialEditText textEmail;

    @BindView(R.id.group_sex)
    RadioGroup groupSex;

    @BindView(R.id.tv_register)
    TextView mTvRegister;
    private RegisterPresenter presenter;
    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.register_layout);
    }

    @Override
    protected void initData() {
        //添加表单验证器
        textUsername.addValidator(new LengthValidator(5,12));
        textPassword.addValidator(new LengthValidator(5,12));
//        textEmail.addValidator(new EmailValidator());
        textPasswordRetry.addValidator(new EmptyValidator("重复密码不能为空！"))
                .addValidator(new PasswordValidator(textPassword.getText()));
        presenter = new RegisterPresenter(RegisterActivity.this,RegisterActivity.this);
    }

//    public void onRegisterResult(Msg msg) {
//        if(msg.getSuccess()){
//            //设置注册按钮为Success
//            button_register.setProgress(100);
//
//            //将username带回登陆界面
//            Intent intent = new Intent();
//            intent.putExtra("username",textUsername.getText().toString());
//            setResult(RESULT_OK,intent);
//            finish();
//        }
//        else {
//            //设置注册按钮为Error，并显示错误信息
//            button_register.setProgress(-1);
//            button_register.setErrorText(msg.getMessage());
//            Toast.makeText(getApplicationContext(),msg.getMessage(),Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void startRegister() {
//        //设置注册按钮为Progress，并设置为无限转动
//        button_register.setIndeterminateProgressMode(true);
//        button_register.setProgress(1);
//    }
//
    @OnClick(R.id.tv_register)
    public void register() {
//        //如果注册按钮状态为Error，则进行归位
//        if(button_register.getProgress() == -1){
//            button_register.setProgress(0);
//        }
//        //先进行表单验证
//        else
            if(textUsername.validate() &&
                 textPassword.validate()&&
                 textPasswordRetry.validate() &&
                 (textEmail.getText().toString().equals("") || textEmail.validate())){
                //包装表单数据并进行注册
                HashMap map = new HashMap();
                map.put("userName",textUsername.getText().toString());
                map.put("mobile",textEmail.getText().toString());
                map.put("password",textPassword.getText().toString());
                presenter.goRegister(map);

//            User user = new User(,);
//
//            user.setEmail(textEmail.getText().toString());
//            RadioButton radioButton = (RadioButton) findViewById(groupSex.getCheckedRadioButtonId());
//            user.setSex(radioButton.getText().toString());
//
//            registerPresenter.register(user);
        }
    }

    @Override
    public void success(String content) {

    }

    @Override
    public void fail(String content) {

    }
}
