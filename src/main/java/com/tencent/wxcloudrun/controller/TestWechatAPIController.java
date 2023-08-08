package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.MessageParam;
import com.tencent.wxcloudrun.dto.TemplateData;
import com.tencent.wxcloudrun.dto.WxTemplate;
import com.tencent.wxcloudrun.service.CounterService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * counter控制器
 */
@RestController

public class TestWechatAPIController {

    public static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/get";

    public static final String SET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry";

    public static final String SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send";

    public static final String GET_TEMPLATE_INFO_URL = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template";

    final CounterService counterService;
    final Logger logger;

    public TestWechatAPIController(@Autowired CounterService counterService) {
        this.counterService = counterService;
        this.logger = LoggerFactory.getLogger(CounterController.class);
    }

    /**
     * 获取所有的粉丝用户列表
     * @return API response json
     */
    @PostMapping(value = "/api/cgi-bin/message/template/send")
    ApiResponse sendTemplateMsg(@RequestBody MessageParam messageParam) {
        String result = sendMsg(messageParam.getOpenId(),messageParam.getTemplate_id(),messageParam.getJumpUrl());
        return ApiResponse.ok(result);
    }


    public String sendMsg(String openId,String template_id,String jumpurl) {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        try {
            WxTemplate wxTemplate = new WxTemplate();
            wxTemplate.setTouser(openId);
            wxTemplate.setTemplate_id(template_id);
            wxTemplate.setUrl(jumpurl);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateString = dateFormat.format(new Date());

            Map<String, TemplateData> m = new HashMap<>();
            m.put("first", new TemplateData("设备告警通知（平台测试消息，无需处理）：","#F7A36F"));
            m.put("keyword1", new TemplateData("demo服务器（192.168.1.14）--测试数据，请勿回复","#79CCE9"));
            m.put("keyword2", new TemplateData("CPU使用率","#79CCE9"));
            m.put("keyword3", new TemplateData(">=90%","#79CCE9"));
            m.put("keyword4", new TemplateData("93%","#79CCE9"));
            m.put("keyword5", new TemplateData(dateString,"#79CCE9"));
            m.put("remark", new TemplateData("，请尽快登录系统进行处理！！！","#FF0000"));
            wxTemplate.setData(m);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(SEND_MESSAGE, wxTemplate, String.class);
            result = responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取所有的粉丝用户列表
     * @return API response json
     */
    @GetMapping(value = "/api/cgi-bin/user/get")
    ApiResponse getUserInfoAPI() {
        String result = getUserInfo();
        return ApiResponse.ok(result);
    }


    /**
     * 获取所有的模板消息列表
     * @return API response json
     */
    @GetMapping(value = "/api/cgi-bin/template/get_all_private_template")
    ApiResponse getTemplateAPI() {
        String result = getTemplate();
        return ApiResponse.ok(result);
    }


    private String getTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(GET_TEMPLATE_INFO_URL,String.class);
            logger.info( GET_TEMPLATE_INFO_URL + " get request");
            result = responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    private String getUserInfo() {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(GET_USER_INFO_URL,String.class);
            logger.info( GET_USER_INFO_URL + " get request");
            result = responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
