package com.example.azuredragon.business.login.activity;

import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azuredragon.IPresenter;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.business.login.activity.http.LoginPresenter;
import com.example.azuredragon.business.login.activity.http.RegisterContract;
import com.example.azuredragon.business.login.activity.http.RegisterPresenter;
import com.example.azuredragon.business.login.validator.EmailValidator;
import com.example.azuredragon.business.login.validator.LengthValidator;
import com.example.azuredragon.business.main.MainActivity;
import com.example.azuredragon.cache.DbHelper;
import com.example.azuredragon.cache.PreferencesUtils;
import com.example.azuredragon.http.bean.LoginBean;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * @author: chz
 * @date: 2018/11/25
 * @description:注册
 */
public class RegisterActivity extends MBaseActivity  implements RegisterContract.View , RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.text_username)
    public MaterialEditText textUsername;

    @BindView(R.id.text_password)
    MaterialEditText textPassword;


    @BindView(R.id.text_email)
    MaterialEditText textEmail;

    @BindView(R.id.group_sex)
    RadioGroup groupSex;

    @BindView(R.id.tv_register)
    TextView mTvRegister;
    private RegisterPresenter presenter;
    /**性别 1-男 2-女*/
    private int sex = 1;

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
        textUsername.addValidator(new LengthValidator(1,12));
        textPassword.addValidator(new LengthValidator(6,12));
        textEmail.addValidator(new EmailValidator());

        presenter = new RegisterPresenter(RegisterActivity.this,RegisterActivity.this);
    }

    @OnClick(R.id.tv_register)
    public void register() {

//         //先进行表单验证
            if (!textEmail.getText().toString().equals("")){
                if (isMobileNO(textEmail.getText().toString())||isEmail(textEmail.getText().toString())){
                    if(textUsername.validate() &&
                            textPassword.validate()) {
                        HashMap map = new HashMap();
                        map.put("mobile", textEmail.getText().toString());
                        presenter.checkPhone(map);
                    }
                }else{
                    textEmail.validate();
                }
            }else{
                textEmail.validate();
            }


    }
    //验证手机号
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    //验证邮箱
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    @Override
    public void success(LoginBean mLoginBean ) {
        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
//        DbHelper.getInstance().getmDaoSession().getLoginBeanDao().insert(mLoginBean);
        //跳转注册界面
        try {
            PreferencesUtils.saveUser(RegisterActivity.this,"user","user_info",mLoginBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void fail(String content) {
        Toast.makeText(RegisterActivity.this,"网络错误",Toast.LENGTH_SHORT).show();;
    }

    @Override
    public void checkPhonesuccess(String content) {
        //包装表单数据并进行注册
        HashMap map = new HashMap();
        map.put("userName", textUsername.getText().toString());
        map.put("mobile", textEmail.getText().toString());
        map.put("password", textPassword.getText().toString());
        map.put("sex", sex);
        presenter.goRegister(map);
    }

    @Override
    public void checkPhonefail(String content) {
        Toast.makeText(RegisterActivity.this,"账号已存在",Toast.LENGTH_SHORT).show();;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.man:
                sex = 1;
                break;
            case R.id.woman:
                sex = 2;
                break;
        }
    }
}
