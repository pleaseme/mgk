package com.example.jpademo.controller;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.services.GuideInfoServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping(value = "/guideinfo")
public class GuideInfoController {

    @Autowired
    private GuideInfoServices guideInfoServices;


    /**提交向导标签*/
    @PostMapping(value = "/add")
    public LBK_Result testadd(HttpServletRequest request,String label) {
        return guideInfoServices.addlabel(request, label);
    }
}
