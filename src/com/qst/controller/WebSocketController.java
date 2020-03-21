package com.qst.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import com.qst.util.WebSocketConfig;
import com.qst.entity.ChatMessage;
import com.qst.entity.User;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

//WebSocket服务器的端点
@ServerEndpoint(value="/websocket",configurator = WebSocketConfig.class)
@Component
public class WebSocketController {

	public static Map<Integer, Session> sessionMap = new ConcurrentHashMap();
	public static Map<Integer, ChatMessage> messageMap = new ConcurrentHashMap();

	//当客户端打开连接：1.添加会话对象 2.更新在线人数
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {

		HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		User user = (User)httpSession.getAttribute("user");
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setFromId(user.getId());
		chatMessage.setStatus(user.getStatus());
		chatMessage.setUserName(user.getName());
		if(sessionMap.containsKey(user.getId())){
			sessionMap.remove(user.getId());
			sessionMap.put(user.getId(),session);
			messageMap.remove(user.getId());
			messageMap.put(user.getId(),chatMessage);
		}else{
			sessionMap.put(user.getId(),session);
			messageMap.put(user.getId(), chatMessage);
		}
		//客服登录更新客服信息
        if(chatMessage.getStatus() == 0){
			//用户登录更新客服信息
			chatMessage = getServerMessage();
			if(chatMessage!=null){
				chatMessage.setContent("客服上线");
				sendMessageTo(chatMessage,session);
			}
		} else if(chatMessage.getStatus() == 4){
			chatMessage.setContent("客服上线");
			sendMessageAll(chatMessage);
		}
	}

	//当关闭连接：1.移除会话对象 2.更新在线人数
	@OnClose
	public void OnClose(Session session) {
		Integer clearKey=0;
		for (Integer key:sessionMap.keySet()){
			if(sessionMap.get(key) == session){
				clearKey = key;
			}
		}
		sessionMap.remove(clearKey);
	}

	//当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
	@OnMessage
	public void OnMessage(String message) {
		ChatMessage chatMessage = JSONObject.parseObject(message,ChatMessage.class);
		sendMessageTo(chatMessage,sessionMap.get(chatMessage.getToId()));
	}
	
	@OnError
	public void error(Session session, Throwable t) {
		t.printStackTrace();
	}

	public void sendMessageTo(ChatMessage chatMessage,Session session){
		//发送字符串
		session.getAsyncRemote().sendText(JSONObject.toJSONString(chatMessage));
		//发送对象，需要配置WebSocket解码器
		//session.getAsyncRemote().sendObject(chatMessage);
	}

	public void sendMessageAll(ChatMessage chatMessage){
		for (Session session_ : sessionMap.values()) {
			session_.getAsyncRemote().sendText(JSONObject.toJSONString(chatMessage));//异步发送消息
			//session_.getBasicRemote().sendText(message);同步发送消息
		}
	}

	public ChatMessage getServerMessage(){
		for (ChatMessage chatMessage : messageMap.values()) {
			if(chatMessage.getStatus()==1){
				return chatMessage;
			}
		}
		return  null;
	}

}
