package com.example.jpademo.utils.weixin.config;

/**
 * @Description:
 * @Date: 2018/7/17
 * @Author: wcf
 */
public class WxPayConfig {
    //小程序appid
    public static final String appid = "wx2a5ddc86b39898d0";
    //微信支付的商户id
    public static final String mch_id = "1523522031";
    //微信支付的商户密钥
    public static final String key = "ox04bi4oaUE4qVuzHDFMDGiV9KL62I4J";
    //支付成功后的服务器回调url
    public static final String notify_url = "http://localhost:8088/weixin/wxNotify";
    //签名方式
    public static final String SIGNTYPE = "MD5";
    //交易类型
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
