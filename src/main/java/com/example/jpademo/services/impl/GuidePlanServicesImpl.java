package com.example.jpademo.services.impl;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.GuidePlan;
import com.example.jpademo.entity.LbkMember;
import com.example.jpademo.jpa.GuidePlanRepository;
import com.example.jpademo.services.GuidePlanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GuidePlanServicesImpl implements GuidePlanServices {

    @Autowired
    private GuidePlanRepository guidePlanRepository;

    /**redis緩存*/
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public LBK_Result releaseplan(HttpServletRequest request, GuidePlan guidePlan) {
        Integer id = getId(request);
        guidePlan.setMemberId(id);
        guidePlanRepository.save(guidePlan);
        return LBK_Result.build(200,"发布成功");
    }

    /**通用方法*/
    public Integer getId(HttpServletRequest request){
        RedisSerializer redisSerializer = new StringRedisSerializer();//序列化
        redisTemplate.setKeySerializer(redisSerializer);
        List<LbkMember> lbkMembers = (List<LbkMember>) redisTemplate.opsForValue().get(request.getHeader("token"));
        return lbkMembers.get(0).getId();
    }

}
