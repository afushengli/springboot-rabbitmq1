package com.fsl.demo.util;

import com.alibaba.fastjson.JSON;
import com.fsl.demo.entity.Order;

/**
 * @author: fsl
 * @date: 2020/1/17 下午6:00
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
public class FastJsonConvertUtil {

    public static String convertObjectToJSON(Order order) {
       return  JSON.toJSONString(order);
    }


    public static Order convertJSONToObject(String message, Class<Order> orderClass) {
       return JSON.parseObject(message,orderClass);
    }
}
