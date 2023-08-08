package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class AlarmMsg {
    private String title;
    private String nodeName;
    private String alarmType;
    private String conditioin;
    private String monitorValue;
    private String monitorTime;
    private String remark;
}
