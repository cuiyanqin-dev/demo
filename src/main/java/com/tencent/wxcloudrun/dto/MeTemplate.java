package com.tencent.wxcloudrun.dto;

/**
 * 微信消息模板
 */
public class MeTemplate {

    // 根据 新增的消息测试模板来定义
    private String title;// 标题
    private String content;// 内容
    private String time;// 时间
    private String remark;// 备注

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
