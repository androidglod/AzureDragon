package com.example.azuredragon.business.login.validator;

import com.rengwuxian.materialedittext.validation.RegexpValidator;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:校验输入框格式
 */
public class EmptyValidator extends RegexpValidator{
    private static final String REGEX = "[^\\s]{1,}";

    public EmptyValidator(String errorMessage){
        super(errorMessage,REGEX);
    }


}
