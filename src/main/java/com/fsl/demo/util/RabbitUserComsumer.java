package com.fsl.demo.util;

import com.fsl.demo.config.RabbitConfig;
import com.fsl.demo.entity.BrokerMessageLog;
import com.fsl.demo.entity.Order;
import com.fsl.demo.entity.User;
import com.fsl.demo.service.BrokerMessageLogService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: fsl
 * @date: 2020/1/17 下午6:40
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */

@Component
@Slf4j
public class RabbitUserComsumer {



    @RabbitListener(queues = RabbitConfig.USER_QUEUE_NAME)
    public String consume(User user) throws IOException {

       log.info("消费了:" +  user.toString());

       return user.toString();
    }



}
