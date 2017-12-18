/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-22 15:23
 */
@Component
@ServerEndpoint("/websocket")
@Slf4j
public class WebSocket {
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> websocketSet = new CopyOnWriteArraySet<>();
    @OnOpen
    public void onOpen(Session session){
        this.session= session;
        websocketSet.add(this);
        log.info("【Websocket消息】 有新的连接，总数:{}",websocketSet.size());
    }

    @OnClose
    public void onClose(Session session){
        this.session= session;
        websocketSet.remove(this);
        log.info("【Websocket消息】 连接断开，总数:{}",websocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【Websocket消息】 收到客户端发来的消息:{}",message);
    }

    public void sendMessage(String message){
        for (WebSocket webSocket:websocketSet) {
            log.info("【Websocket消息】 广播消息:{}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("【Websocket消息】 发送失败:{}",e);
            }
        }
    }
}
