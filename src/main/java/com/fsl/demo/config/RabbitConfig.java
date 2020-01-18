package com.fsl.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: fsl
 * @date: 2020/1/17 下午6:43
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
@Configuration
@Slf4j
public class RabbitConfig {

    // 发送邮件
    public static final String ORDER_QUEUE_NAME = "order.queue";
    public static final String ORDER_EXCHANGE_NAME = "order.exchange";
    public static final String ORDER_ROUTING_KEY_NAME = "order.routing.key";



    public static final String USER_QUEUE_NAME = "user.queue";
    public static final String USER_EXCHANGE_NAME = "user.exchange";
    public static final String USER_ROUTING_KEY_NAME = "user.routing.key";



    /*
     Direct(DirectExchange)：direct 类型的行为是"先匹配, 再投送". 即在绑定时设定一个 routing_key, 消息的routing_key完全匹配时, 才会被交换器投送到绑定的队列中去。
     Topic(TopicExchange)：按规则转发消息（最灵活）。
     Headers(HeadersExchange)：设置header attribute参数类型的交换机。
     Fanout(FanoutExchange)：转发消息到所有绑定队列
     作者：SamHxm
     链接：https://www.jianshu.com/p/e1258c004314
     来源：简书
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(ORDER_ROUTING_KEY_NAME);
    }




    @Bean
    public Queue userQueue() {
        return new Queue(USER_QUEUE_NAME, true);
    }


    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(USER_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding userBinding() {
        return BindingBuilder.bind(userQueue()).to(userExchange()).with(USER_ROUTING_KEY_NAME);
    }




}
