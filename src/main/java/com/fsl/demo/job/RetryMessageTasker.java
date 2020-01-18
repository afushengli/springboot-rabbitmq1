package com.fsl.demo.job;

import com.fsl.demo.dao.BrokerMessageLogDao;
import com.fsl.demo.entity.BrokerMessageLog;
import com.fsl.demo.entity.Order;
import com.fsl.demo.util.Constants;
import com.fsl.demo.util.FastJsonConvertUtil;
import com.fsl.demo.util.RabbitOrderSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author: fsl
 * @date: 2020/1/17 下午6:15
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
@Slf4j
@Component
public class RetryMessageTasker {

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Autowired
    private BrokerMessageLogDao brokerMessageLogDao;

    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void reSend(){
        //pull status = 0 and timeout message

        //查询 是发送状态，或者是发送成功状态
        List<BrokerMessageLog> list = brokerMessageLogDao.query4StatusAndTimeoutMessage();

        list.forEach(msgLog -> {
            String msgId = (msgLog.getMessageId());
            if(msgLog.getTryCount() >= 3){

                //update fail message
                brokerMessageLogDao.changeBrokerMessageLogStatus(msgLog.getMessageId(), Constants.ORDER_SEND_FAILURE, new Date());
                log.info("超过最大重试次数, 消息投递失败, msgId: {}", msgId);
            } else {
                // 更新投递次数
                brokerMessageLogDao.update4ReSend(msgLog.getMessageId(),  new Date());

                Order reSendOrder = FastJsonConvertUtil.convertJSONToObject(msgLog.getMessage(), Order.class);
                try {
                    rabbitOrderSender.sendOrder(reSendOrder);  // 重新投递

                    log.info("第 " + (msgLog.getTryCount() + 1) + " 次重新投递消息");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("-----------异常处理-----------");
                }
            }
        });
        log.info("定时任务执行结束(重新投递消息)");
    }

}
