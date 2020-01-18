package com.fsl.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fsl.demo.dao.BrokerMessageLogDao;
import com.fsl.demo.entity.BrokerMessageLog;
import com.fsl.demo.service.BrokerMessageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: fsl
 * @date: 2020/1/17 下午7:00
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
@Service
public class BrokerMessageLogServiceImpl  implements BrokerMessageLogService {

    @Autowired
    private BrokerMessageLogDao rokerMessageLogDao;

    @Override
    public BrokerMessageLog selectByMsgId(String msgId) {

        EntityWrapper query  = new EntityWrapper();
        query.eq("message_id",msgId);
        List<BrokerMessageLog> list = rokerMessageLogDao.selectList(query);
        return list.get(0);
    }


    @Override
    public void updateStatus(String msgId, String consumedSuccess) {
        rokerMessageLogDao.changeBrokerMessageLogStatus(msgId,consumedSuccess,new Date());
    }


}
