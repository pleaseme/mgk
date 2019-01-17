package com.example.jpademo.controller;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.GuideVerify;
import com.example.jpademo.services.GuideVerifyServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping(value = "/guide")
public class GuideVerifyController {


    @Autowired
    private GuideVerifyServices guideVerifyServices;

/**提交向导验证信息*/
    @PostMapping(value = "/add")
    public LBK_Result testadd(GuideVerify guideVerify, HttpServletRequest request) {
        return guideVerifyServices.commit(guideVerify,request);
    }


/**测试*/
    @RequestMapping(value = "/test")
    public LBK_Result testneew() {
        return guideVerifyServices.selectallguide();
    }

}
