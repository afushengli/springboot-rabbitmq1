package com.fsl.demo.util;

import com.fsl.demo.config.RabbitConfig;
import com.fsl.demo.dao.BrokerMessageLogDao;
import com.fsl.demo.entity.Order;
import com.fsl.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: fsl
 * @date: 2020/1/17 下午5:44
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
@Slf4j
@Component
public class RabbitUserSender {

        //自动注入RabbitTemplate模板类
        @Autowired
        private RabbitTemplate rabbitTemplate;


        //发送消息方法调用: 构建自定义对象消息
        public String sendUser(User user) {
            //消息唯一ID
           return  (String)rabbitTemplate.convertSendAndReceive(RabbitConfig.USER_EXCHANGE_NAME , RabbitConfig.USER_ROUTING_KEY_NAME ,user );
        }
}
