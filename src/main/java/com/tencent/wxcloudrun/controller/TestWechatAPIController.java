package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.CounterService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * counter控制器
 */
@RestController

public class TestWechatAPIController {


    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/get";

    final CounterService counterService;
    final Logger logger;

    public TestWechatAPIController(@Autowired CounterService counterService) {
        this.counterService = counterService;
        this.logger = LoggerFactory.getLogger(CounterController.class);
    }

    /**
     * 获取当前计数
     * @return API response json
     */
    @GetMapping(value = "/api/cgi-bin/user/get")
    ApiResponse get() {
        logger.info("/api/cgi-bin/user/get get request");
        String result = getTemplate();
        return ApiResponse.ok(result);
    }


    private String getTemplate() {
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
