package com.fsl.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fsl.demo.dao.BrokerMessageLogDao;
import com.fsl.demo.dao.OrderDao;
import com.fsl.demo.entity.BrokerMessageLog;
import com.fsl.demo.entity.Order;
import com.fsl.demo.service.OrderService;
import com.fsl.demo.util.Constants;
import com.fsl.demo.util.FastJsonConvertUtil;
import com.fsl.demo.util.RabbitOrderSender;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: fsl
 * @date: 2020/1/18 上午9:22
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
@Service
public class OrderSeviceImpl implements OrderService {


    @Autowired
    private BrokerMessageLogDao brokerMessageLogDao;

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Autowired
    private OrderDao orderDao;

    public void createOrder(Order order) throws Exception {
        // 使用当前时间当做订单创建时间（为了模拟一下简化）
        Date orderTime = new Date();
        // 插入业务数据
        orderDao.insert(order);
        // 插入消息记录表数据
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        // 消息唯一ID
        brokerMessageLog.setMessageId(order.getMessageId());
        // 保存消息整体 转为JSON 格式存储入库
        brokerMessageLog.setMessage(FastJsonConvertUtil.convertObjectToJSON(order));
        // 设置消息状态为0 表示发送中
        brokerMessageLog.setStatus("0");
        // 设置消息未确认超时时间窗口为 一分钟
        brokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLog.setUpdateTime(new Date());

        brokerMessageLogDao.insert(brokerMessageLog);
        // 发送消息
        rabbitOrderSender.sendOrder(order);
    }

    @Override
    public  Page<Order> selectOrderByPage(Map<String,Object> params) {

        int currentPage = Integer.parseInt(params.get("page").toString());
        int size = Integer.parseInt(params.get("limit").toString());
        Page<Order> page = new Page (currentPage,size);

        EntityWrapper queryWrapper = new EntityWrapper();
        /*queryWrapper.eq("deleted","0");
        String companyName =  params.get("companyName").toString();

        if(StringUtils.isNotBlank(companyName)){
            queryWrapper.like("company_name",companyName);
        }


        Boolean createTime = Boolean.valueOf(params.get("createTime").toString());

        if(createTime){
            Boolean cretateTimeOrder = Boolean.valueOf(params.get("createTimeOrder").toString());
            queryWrapper.orderBy("create_time",cretateTimeOrder);
        }else{
            queryWrapper.orderBy("create_time",false);
        }
*/
        List<Order> primeProjCompanies = orderDao.selectPage(page, queryWrapper);

        page.setRecords(primeProjCompanies);

        return page;
    }


}
