package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class MessageParam {
    /**
     * openid
     */
    private String openId;
    private String template_id;
    private String jumpUrl;
    private String first;
    private AlarmMsg data;
}
