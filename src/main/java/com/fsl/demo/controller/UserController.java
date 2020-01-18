package com.fsl.demo.controller;

import com.fsl.demo.entity.User;
import com.fsl.demo.util.RabbitUserSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fsl
 * @date: 2020/1/18 下午4:15
 * @description:
 * @version: Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
@RestController
@RequestMapping("/user")
public class UserController {



    @Autowired
    private RabbitUserSender rabbitUserSender;


    //转换并发送消息,且等待消息者返回响应消息。
    @GetMapping("/sendUser")
    public  String  sendUser(User user){
      return   rabbitUserSender.sendUser(user);
    }



}
