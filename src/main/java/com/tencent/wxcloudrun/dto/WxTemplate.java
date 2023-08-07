package com.tencent.wxcloudrun.dto;

import java.util.Map;

/**
 * 微信消息参数
 */
public class WxTemplate {

    //进行推送所需参数
    private String touser;//用户openid（接收人）
    private String template_id;//消息模版id
    private String url = "https://baidu.com";//跳转到某链接或某页面
    private Map<String, TemplateData> data;//要推送的模板内容

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }

}