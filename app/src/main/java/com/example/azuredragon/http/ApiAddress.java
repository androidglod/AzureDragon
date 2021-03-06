package com.example.azuredragon.http;

/**
 * @author: chz
 * @date: 2018/11/25
 * @description:所有接口地址集
 */
public class ApiAddress {
    //生成环境
    public final static String apis = "http://www.tv188.com/";

    //生成环境
    public final static String api = "http://www.qinglongread.com/app/";


    /***********************首页*******************************/
    //banner
    public final static String getBannerList = "mobile/listSlides.do";
    //获取直播数据
    public final static String getLivesList = "mobile/listLives.do";
    //最新资讯
    public final static String getZixunList = "works/findWorksList.do";



    /**************************************个人中心************************************************/
    //忘记密码
    public final static String forgetPwd = "mbforgetPwd.do";
    //登录
//    public final static String userLogin = "mblogin.do";
    //注册
//    public final static String userRegister = "mbregister.do";
    //发送验证码
    public final static String sendVerifyCode = "mbsend";

    //获取图片验证码
    public final static String getVerifyCode = "getVerityCode.do";

    //登录
    public final static String userLogin = "reader/login.do";
    //注册
    public final static String userRegister = "reader/regist.do";
    //小说列表
    public final static String bookList = "http://www.qinglongread.com/app/works/findWorksList.do";
    //小说详情
    public final static String bookDetail = "  http://www.qinglongread.com/app/works/detailWorks.do";
    //作品列表
    public final static String myBooklists = " http://www.qinglongread.com/app/works/showChapterList.do";


}
