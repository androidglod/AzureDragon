package com.example.azuredragon.business.login.validator;

import android.support.annotation.NonNull;
import android.text.Editable;

import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:校验输入框格式
 */

public class PasswordValidator extends METValidator{
    private Editable password;

    public PasswordValidator(Editable password){
        super("输入的两次密码不一致！");
        this.password = password;
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        return text.toString().equals(password.toString());
    }
}
