package com.fsl.demo.util;

import com.fsl.demo.config.RabbitConfig;
import com.fsl.demo.entity.BrokerMessageLog;
import com.fsl.demo.entity.Order;
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
public class RabbitOrderComsumer {

    @Autowired
  private BrokerMessageLogService brokerMessageLogServiceImpl;


    @RabbitListener(queues = RabbitConfig.ORDER_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {

        Order order = FastJsonConvertUtil.convertJSONToObject(new String(message.getBody()), Order.class);
        log.info("收到消息: {}", order.toString());

        String msgId = order.getMessageId();


        BrokerMessageLog msgLog = brokerMessageLogServiceImpl.selectByMsgId(msgId);

        // 消费幂等性
        if (null == msgLog || msgLog.getStatus().equals(Constants.CONSUMED_SUCCESS)) {
            log.info("重复消费, msgId: {}", msgId);
            return;
        }

        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();

        // 此处是拿到消息去做自己要做的事情，比如发送消息，减去库存，等等之类的
        // boolean success = mailUtil.send(mail);

        boolean success = true;

        if (success) {

            log.info("消息消费,消息确认成功");
            brokerMessageLogServiceImpl.updateStatus(msgId, Constants.CONSUMED_SUCCESS);
            // 消费确认
            channel.basicAck(tag, false);
        } else {

            //会触发: channel.basicNack(tag, false, true);, 这样会告诉rabbitmq该消息消费失败, 需要重新入队, 可以重新投递到其他正常的消费端进行消费, 从而保证消息不被丢失
            channel.basicNack(tag, false, true);

        }
    }



}
