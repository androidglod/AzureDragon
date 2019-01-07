package com.example.azuredragon.business.pay;

import android.text.Editable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.azuredragon.IPresenter;
import com.example.azuredragon.MBaseActivity;
import com.example.azuredragon.R;
import com.example.azuredragon.R2;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.ta.utdid2.android.utils.StringUtils;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description：支付
 */
public class PayActivity extends MBaseActivity {

    @BindView(R.id.shiyuan)
    RelativeLayout shiyuan;
    @BindView(R.id.ch_box_10)
    ImageView mCk_10;
    @BindView(R.id.ch_box_20)
    ImageView mCk_20;
    @BindView(R.id.ch_box_50)
    ImageView mCk_50;
    @BindView(R.id.ch_box_100)
    ImageView mCk_100;
    @BindView(R.id.ch_custom)
    ImageView mCkcustom;
    @BindView(R.id.me_custom)
    MaterialEditText mCkmeCustom;
    @BindView(R.id.tv_pay)
    TextView mBtnPay;

    @OnClick(R.id.iv_return)
    public void back(){
        finish();
    }

    @OnClick(R.id.shiyuan)
    public void shiyuan(){
        select(R.id.ch_box_10);
    }

    @OnClick(R.id.ershi)
    public void ershi(){
        select(R.id.ch_box_20);
    }

    @OnClick(R.id.wushi)
    public void wushi(){
        select(R.id.ch_box_50);
    }

    @OnClick(R.id.yibaiyuan)
    public void yibaiyuan(){
        select(R.id.ch_box_100);
    }

    @OnClick(R.id.rl_custom)
    public void custom(){
        select(R.id.ch_custom);
    }

    @OnClick(R.id.me_custom)
    public void custom1(){
        select(R.id.ch_custom);
    }

    @OnClick(R.id.tv_pay)
    public void pay(){
    }


    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void bindView() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void firstRequest() {

    }

    /**
     * 监听输入框title
     * @param editable 文本
     */
    @OnTextChanged(value = R2.id.me_custom, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChangedForName(Editable editable) {
        String price = editable.toString();
        if (!StringUtils.isEmpty(price)){
            Double d= Double.parseDouble(price);
            DecimalFormat df = new DecimalFormat("0.00");
            String s = df.format(d/100);
            mBtnPay.setText("支付"+s+"元");
        }

    }

    protected void select(int id) {
        mCk_10.setImageResource(R.drawable.consultation_select);
        mCk_20.setImageResource(R.drawable.consultation_select);
        mCk_50.setImageResource(R.drawable.consultation_select);
        mCk_100.setImageResource(R.drawable.consultation_select);
        mCkmeCustom.setText("");
        mCkmeCustom.setFocusable(false);
        mCkmeCustom.setFocusableInTouchMode(false);
        mCkcustom.setImageResource(R.drawable.consultation_select);
        switch (id){
            case R.id.ch_box_10:
                mBtnPay.setText("支付10元");
                mCk_10.setImageResource(R.drawable.consultation_selected);
                break;
            case R.id.ch_box_20:
                mBtnPay.setText("支付20元");
                mCk_20.setImageResource(R.drawable.consultation_selected);
                break;
            case R.id.ch_box_50:
                mBtnPay.setText("支付50元");
                mCk_50.setImageResource(R.drawable.consultation_selected);
                break;
            case R.id.ch_box_100:
                mBtnPay.setText("支付100元");
                mCk_100.setImageResource(R.drawable.consultation_selected);
                break;
            case R.id.ch_custom:
                mBtnPay.setText("支付0元");
                mCkmeCustom.setFocusable(true);
                mCkmeCustom.setFocusableInTouchMode(true);
                mCkmeCustom.requestFocus();
                mCkcustom.setImageResource(R.drawable.consultation_selected);
                break;
        }
    }

}
