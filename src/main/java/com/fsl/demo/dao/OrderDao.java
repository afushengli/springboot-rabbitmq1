package com.fsl.demo.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fsl.demo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: fsl
 * @date: 2020/1/17 下午6:18
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
@Mapper
public interface OrderDao  extends BaseMapper<Order> {
}
