package com.fsl.demo.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.fsl.demo.entity.Order;
import com.fsl.demo.service.OrderService;
import com.fsl.demo.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * @author: fsl
 * @date: 2020/1/18 上午9:25
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService OrderSeviceImpl;


    @GetMapping("/createOrder")
    private void createOrder(Order order){

       // Order order = new Order();
      //  order.setId("2018080400000007");
        order.setName("测试订单");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());


        try {
            OrderSeviceImpl.createOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 功能描述
     * @author fsl
     * @date 2020/1/18 下午2:53
     * @return
     *  pagedate: {
     *         page: 1,
     *         limit: 10,
     *         total: 0,
     *         createTime: true,
     *         createTimeOrder: true
     *       }
     *
     */

    @GetMapping("/getPage")
    private PageUtils getPage(@RequestBody Map<String,Object > params) {

        Page<Order> page = OrderSeviceImpl.selectOrderByPage(params);
       return new PageUtils(page);
    }






}
