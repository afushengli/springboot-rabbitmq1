package com.fsl.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fsl.demo.entity.BrokerMessageLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: fsl
 * @date: 2020/1/17 下午5:36
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
@Mapper
public interface BrokerMessageLogDao extends BaseMapper<BrokerMessageLog> {


    /**
     * 查询消息状态为0(发送中) 且已经超时的消息集合
     * @return
     */
    List<BrokerMessageLog> query4StatusAndTimeoutMessage();

    /**
     * 重新发送统计count发送次数 +1
     * @param messageId
     * @param updateTime
     */
    void update4ReSend(@Param("messageId")String messageId, @Param("updateTime")Date updateTime);
    /**
     * 更新最终消息发送结果 成功 or 失败
     * @param messageId
     * @param status
     * @param updateTime
     */
    void changeBrokerMessageLogStatus(@Param("messageId")String messageId, @Param("status")String status, @Param("updateTime")Date updateTime);

}
