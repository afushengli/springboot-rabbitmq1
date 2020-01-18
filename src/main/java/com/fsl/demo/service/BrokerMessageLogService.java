package com.fsl.demo.service;

import com.fsl.demo.entity.BrokerMessageLog;

/**
 * @author: fsl
 * @date: 2020/1/17 下午6:57
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
public interface BrokerMessageLogService {

    BrokerMessageLog selectByMsgId(String msgId);

    void updateStatus(String msgId, String consumedSuccess);
}
