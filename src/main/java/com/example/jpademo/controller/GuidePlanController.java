package com.example.jpademo.controller;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.GuidePlan;
import com.example.jpademo.services.GuidePlanServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping(value = "/guide")
public class GuidePlanController  {

    @Autowired
    private GuidePlanServices guidePlanServices;


    /**发布方案*/
    @PostMapping(value = "/releaseplan")
    public LBK_Result testadd(HttpServletRequest request, GuidePlan guidePlan) {
        return guidePlanServices.releaseplan(request, guidePlan);
    }
}
