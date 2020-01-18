package com.fsl.demo.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fsl.demo.entity.Order;

import java.util.Map;

/**
 * @author: fsl
 * @date: 2020/1/17 下午5:42
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
public interface  OrderService {


     void createOrder(Order order) throws Exception;


     Page<Order> selectOrderByPage(Map<String,Object> params);
}
