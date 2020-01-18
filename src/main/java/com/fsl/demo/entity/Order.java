package com.fsl.demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author: fsl
 * @date: 2020/1/17 下午5:27
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 9111357402963030257L;

    private String id;

    private String name;

    private String messageId;
}

