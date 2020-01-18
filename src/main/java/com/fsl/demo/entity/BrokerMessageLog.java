package com.fsl.demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: fsl
 * @date: 2020/1/17 下午5:29
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "broker_message_log")
public class BrokerMessageLog {

    private String messageId;

    private String message;

    private Integer tryCount;

    private String status;

    private Date nextRetry;

    private Date createTime;

    private Date updateTime;

}
