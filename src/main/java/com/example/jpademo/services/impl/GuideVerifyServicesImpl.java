package com.example.jpademo.services.impl;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.GuideVerify;
import com.example.jpademo.entity.LbkMember;
import com.example.jpademo.jpa.GuideVerifyRepository;
import com.example.jpademo.services.GuideVerifyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GuideVerifyServicesImpl implements GuideVerifyServices {


    @Autowired
    private GuideVerifyRepository guideVerifyRepository;


    /**redis緩存*/
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public LBK_Result commit(GuideVerify guideVerify, HttpServletRequest request) {
        Integer id = getIdBytoken(request);
        System.out.println(id);
        guideVerify.setMemberId(id);
        guideVerifyRepository.save(guideVerify);
        return LBK_Result.build(200,"提交成功");
    }

    @Override
    public LBK_Result selectallguide() {
        List<GuideVerify> all = guideVerifyRepository.findAll();
        return LBK_Result.ok(all);
    }


/**通用方法*/
    public Integer getIdBytoken(HttpServletRequest request){
        RedisSerializer redisSerializer = new StringRedisSerializer();//序列化
        redisTemplate.setKeySerializer(redisSerializer);
        String token = request.getHeader("token");//獲取请求头的token
        List<LbkMember> lbkMembers = (List<LbkMember>) redisTemplate.opsForValue().get(token);
        Integer id = lbkMembers.get(0).getId();//获取token里面的id
        System.out.println(id);
        return id;
    }

}
