package com.tencent.wxcloudrun.dto;

/**
 * 微信模版消息内容
 */
public class TemplateData {

    private String value;//消息内容
    private String color;//消息颜色

    //构造方法（空参）
    public TemplateData() {
    }

    //构造方法（有参）
    public TemplateData(String value,String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}