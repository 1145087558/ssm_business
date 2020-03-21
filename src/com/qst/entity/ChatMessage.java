package com.qst.entity;


import java.io.Serializable;

public class ChatMessage implements Serializable {

    private Integer toId;//接收者用户ID
    private Integer fromId;//发送者用户ID
    private String content;//发给某个用户的信息
    private String userName;//接收者的名称
    private Integer status;//发送者状态（0为用户，4为客服）
    private String type;//区别登录时还是发送消息
	public Integer getToId() {
		return toId;
	}
	public void setToId(Integer toId) {
		this.toId = toId;
	}
	public Integer getFromId() {
		return fromId;
	}
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
    
    
}
