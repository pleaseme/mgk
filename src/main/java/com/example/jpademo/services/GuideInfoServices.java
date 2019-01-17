package com.example.jpademo.services;

import com.example.jpademo.common.LBK_Result;

import javax.servlet.http.HttpServletRequest;

public interface GuideInfoServices {

    //添加向导标签
    LBK_Result addlabel(HttpServletRequest request,String label);



}
