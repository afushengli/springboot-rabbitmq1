package com.fsl.demo.util;

import com.fsl.demo.config.RabbitConfig;
import com.fsl.demo.dao.BrokerMessageLogDao;
import com.fsl.demo.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
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
public class RabbitOrderSender {

        //自动注入RabbitTemplate模板类
        @Autowired
        private RabbitTemplate rabbitTemplate;

        @Autowired
        private BrokerMessageLogDao brokerMessageLogDao;

        //回调函数: confirm确认
        RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {

                System.err.println("correlationData: " + correlationData);
                String messageId = correlationData.getId();

                if(ack){
                    //如果confirm返回成功 则进行更新
                    brokerMessageLogDao.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS, new Date());
                } else {
                    //失败则进行具体的后续操作:重试 或者补偿等手段
                    log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
                }
            }

        };






        //发送消息方法调用: 构建自定义对象消息
        public void sendOrder(Order order) throws Exception {


            rabbitTemplate.setConfirmCallback(confirmCallback);
            rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
                log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
            });


            //消息唯一ID
            CorrelationData correlationData = new CorrelationData(order.getMessageId());
            String orderStr = FastJsonConvertUtil.convertObjectToJSON(order);
            rabbitTemplate.convertAndSend(RabbitConfig.ORDER_EXCHANGE_NAME , RabbitConfig.ORDER_ROUTING_KEY_NAME , orderStr, correlationData);


        }
}
