package com.example.jpademo.services;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.GuideVerify;

import javax.servlet.http.HttpServletRequest;

public interface GuideVerifyServices {


    //提交向岛认证信息
    LBK_Result commit(GuideVerify guideVerify, HttpServletRequest request);



    //查询所有认证向导信息
    LBK_Result selectallguide();



}
