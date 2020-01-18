package com.fsl.demo;

import com.fsl.demo.entity.Order;
import com.fsl.demo.util.RabbitOrderSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SpringbootRabbitmq1ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Test
    public void testSender2() throws Exception {
        Order order = new Order();
        order.setId("2018080400000001");
        order.setName("测试订单");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        rabbitOrderSender.sendOrder(order);
    }

}
