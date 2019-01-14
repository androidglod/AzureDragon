package com.example.azuredragon.business.seting;

import android.app.Dialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azuredragon.IPresenter;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.R2;
import com.example.azuredragon.business.login.activity.LoginActivity;
import com.example.azuredragon.cache.PreferencesUtils;
import com.example.azuredragon.http.bean.LoginBean;
import com.ta.utdid2.android.utils.StringUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:意见反馈
 */
public class FeedbackActivity extends MBaseActivity implements FeedbackContract.View{


    @BindView(R2.id.et_content)
    EditText mEtContent;

    @BindView(R2.id.text_count)
    TextView mTvContent;
    @OnClick(R2.id.iv_return)
    public void back(){
        finish();
    }


    @OnClick(R2.id.tv_submit)
    public void submit(){
        if (StringUtils.isEmpty( mEtContent.getText().toString())){
            Toast.makeText(FeedbackActivity.this,"请填写反馈内容",Toast.LENGTH_SHORT).show();
        }else{
            LoginBean mLoginBeans = PreferencesUtils.getUser(this,"user","user_info");
            if (null == mLoginBeans){
                //登录
                Intent mIntent = new Intent(FeedbackActivity.this,LoginActivity.class);
                startActivity(mIntent);
            }else{
                HashMap map = new HashMap();
                map.put("readerId", mLoginBeans.getId());
                map.put("cotent", mEtContent.getText().toString());
                presenter.goFeedback(map);
            }



        }

    }

    private FeedbackPresenter presenter;

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_feekback_dragon);
    }

    @Override
    protected void initData() {
        presenter = new FeedbackPresenter(this,this);
        mEtContent.addTextChangedListener(new TextWatcher() { //对EditText进行监听
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTvContent.setText("剩余字数：" + (200 - editable.length()));
            }
        });

    }


    @Override
    public void success() {
        Toast.makeText(FeedbackActivity.this,"感谢您的反馈",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void fail( ) {
        Toast.makeText(FeedbackActivity.this,"请检查网络设置",Toast.LENGTH_SHORT).show();
    }
}