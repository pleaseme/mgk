package com.example.jpademo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.jpademo.common.DateUtil;
import com.example.jpademo.entity.LbkMember;
import com.example.jpademo.services.MemberServices;
import com.example.jpademo.utils.IpUtils;
import com.example.jpademo.utils.Json;
import com.example.jpademo.utils.StringUtils;
import com.example.jpademo.utils.weixin.config.WxPayConfig;
import com.example.jpademo.utils.weixin.vo.OAuthJsToken;
import com.example.jpademo.utils.weixin.vo.PayUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weixin4j.WeixinException;
import org.weixin4j.WeixinSupport;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Description: 本示例仅供参考，请根据自己的使用情景进行修改
 * @Date: 2018/7/17
 * @Author: wcf
 */
@RequestMapping("/weixin")
@RestController
public class WeixinController extends WeixinSupport {

/**redis緩存*/
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private MemberServices memberServices;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String appid = "wx2a5ddc86b39898d0";	    //微信小程序appid
    private static final String secret = "808e73e9ebb639585d79d7090e2c0444";	//微信小程序密钥
    private static final String grant_type = "authorization_code";

    /**
     * 小程序后台登录，向微信平台发送获取access_token请求，并返回openId
     *
     * @param code
     * @return openid
     * @throws WeixinException
     * @throws IOException
     * @since Weixin4J 1.0.0
     * 用戶提交code,头像昵称信息，根据code获取oppenid，然后存入redis数据库,返回token
     */
    @PostMapping("login")
    public Map<String, Object> login(String code, HttpServletRequest request,String img,String nickname) throws WeixinException, IOException {
        if (code == null || code.equals("")) {
            throw new WeixinException("invalid null, code is null.");
        }

        Map<String, Object> ret = new HashMap<String, Object>();
        //拼接参数
        String param = "?grant_type=" + grant_type + "&appid=" + appid + "&secret=" + secret + "&js_code=" + code;

        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.get("https://api.weixin.qq.com/sns/jscode2session" + param);
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj != null) {
            Object errcode = jsonObj.get("errcode");
            if (errcode != null) {
                //返回异常信息
                throw new WeixinException(getCause(Integer.parseInt(errcode.toString())));
            }

            ObjectMapper mapper = new ObjectMapper();
            OAuthJsToken oauthJsToken = mapper.readValue(jsonObj.toJSONString(),OAuthJsToken.class);

            //redis
            RedisSerializer redisSerializer = new StringRedisSerializer();//序列化
            redisTemplate.setKeySerializer(redisSerializer);

            //生成token,插入token信息
            String token = UUID.randomUUID().toString();
            LbkMember lbkMember=new LbkMember();
            lbkMember.setAvatar(img);
            lbkMember.setNickname(nickname);
            lbkMember.setWeixinOpenid(oauthJsToken.getOpenid());
            lbkMember.setCreateTime( DateUtil.getFormatDate(new Date()));
            lbkMember.setIntegral(0);
            lbkMember.setMemberStatus(0);
            memberServices.add(lbkMember);
            List<LbkMember> findbynickname = memberServices.findbynickname(nickname);
            Integer id = findbynickname.get(0).getId();//得到用戶id
            List<LbkMember> findbyid = memberServices.findbyid(id);//得到用戶的最新信息
            redisTemplate.opsForValue().set(token,findbyid);//將用戶信息攝入reids緩存

            //測試用戶緩存成功（查看用戶信息）
            List<LbkMember> lbkMembers= (List<LbkMember>) redisTemplate.opsForValue().get(token);
            System.out.println(lbkMembers.get(0));


            logger.info("openid=" + oauthJsToken.getOpenid());
             ret.put("token",token);
            ret.put("openid", oauthJsToken.getOpenid());
            ret.put("session_key ",oauthJsToken.getSession_key());
        }
        return ret;
    }

    /**
     * @Description: 发起微信支付
     * @param openid
     * @param request
     * @author: wcf
     * @date: 2018年7月17日
     */
    @RequestMapping("wxPay")
    public Json wxPay(String openid, HttpServletRequest request){
        Json json = new Json();
        try{
            //生成的随机字符串
            String nonce_str = StringUtils.getRandomStringByLength(32);
            //商品名称
            String body = "测试商品名称";
            //获取本机的ip地址
            String spbill_create_ip = IpUtils.getIpAddr(request);

            String orderNo = "123435122";
            String money = "1";//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败

            Map<String, String> packageParams = new HashMap<>();
            packageParams.put("appid", WxPayConfig.appid);
            packageParams.put("mch_id", WxPayConfig.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", orderNo);//商户订单号
            packageParams.put("total_fee", money);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WxPayConfig.notify_url);
            packageParams.put("trade_type", WxPayConfig.TRADETYPE);
            packageParams.put("openid", openid);

            // 除去数组中的空值和签名参数
            packageParams = PayUtil.paraFilter(packageParams);
            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();
            logger.info("=======================第一次签名：" + mysign + "=====================");
            
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WxPayConfig.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WxPayConfig.notify_url + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + orderNo + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + money + "</total_fee>"
                    + "<trade_type>" + WxPayConfig.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";


                    System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);

            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WxPayConfig.pay_url, "POST", xml);

            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);

            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);
            System.out.println(map);

            String return_code = (String) map.get("return_code");//返回状态码
            System.out.println(return_code);//打印状态码

           //返回给移动端需要的参数
            Map<String, Object> response = new HashMap<String, Object>();
            if(return_code == "SUCCESS" || return_code.equals(return_code)){
                // 业务结果
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误

                String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=" + WxPayConfig.SIGNTYPE + "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.key, "utf-8").toUpperCase();
                logger.info("=======================第二次签名：" + paySign + "=====================");

                response.put("paySign", paySign);
                //更新订单信息
                //业务逻辑代码
            }
            response.put("appid", WxPayConfig.appid);

            json.setSuccess(true);
           json.setData(response);
        }catch(Exception e){
            e.printStackTrace();
            json.setSuccess(false);
            json.setMsg("发起失败");
        }
        return json;
    }

    /**
     * @Description:微信支付
     * @return
     * @author dzg
     * @throws Exception
     * @throws WeixinException
     * @date 2018年7月17日
     */
    @RequestMapping(value="/wxNotify")
    public void wxNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);

        Map map = PayUtil.doXMLParse(notityXml);

        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            //验证签名是否正确
            if(PayUtil.verify(PayUtil.createLinkString(map), (String)map.get("sign"), WxPayConfig.key, "utf-8")){
                /**此处添加自己的业务逻辑代码start**/


                /**此处添加自己的业务逻辑代码end**/

                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");

        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }
}
