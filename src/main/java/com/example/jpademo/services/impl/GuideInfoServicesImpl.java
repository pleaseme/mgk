package com.example.jpademo.services.impl;

import com.example.jpademo.common.LBK_Result;
import com.example.jpademo.entity.GuideInfo;
import com.example.jpademo.entity.LbkMember;
import com.example.jpademo.jpa.GuideInfoRepository;
import com.example.jpademo.services.GuideInfoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GuideInfoServicesImpl implements GuideInfoServices {


    @Autowired
    private GuideInfoRepository guideInfoRepository;

    /**redis緩存*/
    @Autowired
    protected RedisTemplate<Object,Object> redisTemplate;

/**添加向导标签*/
    @Override
    public LBK_Result addlabel(HttpServletRequest request, String label) {
        Integer id = getId(request);
        System.out.println(id);//打印向导id
        GuideInfo guideInfo=new GuideInfo();
        guideInfo.setMemberId(id);
        guideInfo.setLabel(label);
        guideInfoRepository.save(guideInfo);
        return LBK_Result.build(200,"标签设置成功");
    }

    public Integer getId(HttpServletRequest request){
        RedisSerializer redisSerializer = new StringRedisSerializer();//序列化
        redisTemplate.setKeySerializer(redisSerializer);
        List<LbkMember> lbkMembers = (List<LbkMember>) redisTemplate.opsForValue().get(request.getHeader("token"));
        Integer id = lbkMembers.get(0).getId();//获取token里面的id
        System.out.println(id);
        return id;
    }





}
