package com.example.jpademo.services;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.GuidePlan;

import javax.servlet.http.HttpServletRequest;

public interface GuidePlanServices {

    //发布方案
    LBK_Result releaseplan(HttpServletRequest request, GuidePlan guidePlan);

}
