package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * counter控制器
 */
@RestController

public class TestWechatAPIController {


    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/get";

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
    ApiResponse sendTemplateMsg(@RequestBody String data) {
        String result = sendMsg(data);
        return ApiResponse.ok(result);
    }

    public String sendMsg(String data) {
        logger.info( SEND_MESSAGE + " get request");
        String result = "";
        HttpPost post = new HttpPost(SEND_MESSAGE);
        StringEntity entity = new StringEntity(data,"utf-8");
        post.setEntity(entity);
        try {
            HttpEntity resultEntity = httpclient.execute(post).getEntity();
            result = EntityUtils.toString(resultEntity);
        } catch (IOException e) {
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
        String result = "";
        try {
            logger.info( GET_TEMPLATE_INFO_URL + " get request");
            HttpGet httpGet = new HttpGet(GET_TEMPLATE_INFO_URL);
            HttpEntity entity = httpclient.execute(httpGet).getEntity();
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private String sendTemplate() {
        String result = "";
        try {
            logger.info( GET_TEMPLATE_INFO_URL + " get request");
            HttpGet httpGet = new HttpGet(GET_TEMPLATE_INFO_URL);
            HttpEntity entity = httpclient.execute(httpGet).getEntity();
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private String getUserInfo() {
        String result = "";
        try {
            logger.info( GET_USER_INFO_URL + " get request");
            HttpGet httpGet = new HttpGet(GET_USER_INFO_URL);
            HttpEntity entity = httpclient.execute(httpGet).getEntity();
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
