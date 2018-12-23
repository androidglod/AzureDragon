package com.example.azuredragon.business.login.validator;

import com.rengwuxian.materialedittext.validation.RegexpValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:校验输入框格式
 */

public class EmailValidator extends RegexpValidator{
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    public EmailValidator(){
        super("请输入手机号或邮箱",EMAIL_REGEX);
    }



}
