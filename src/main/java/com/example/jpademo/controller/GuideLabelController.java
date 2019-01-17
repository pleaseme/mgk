package com.example.jpademo.controller;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.services.GuideLabelServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/guidelabel")
public class GuideLabelController  {


    @Autowired
    private GuideLabelServices guideLabelServices;


    /**
     * 获取所有标签
     * */
    @RequestMapping(value = "/get")
    public LBK_Result testneew() {
        return guideLabelServices.getLabel();
    }
}
