package com.qst.entity;

public class Discuss {

	private int id;
	private int user_id;
	private int opus_id;
	private String opus_name;
	private String user_name;
	private String discuss_msg;
	private String discuss_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getOpus_id() {
		return opus_id;
	}
	public void setOpus_id(int opus_id) {
		this.opus_id = opus_id;
	}
	public String getOpus_name() {
		return opus_name;
	}
	public void setOpus_name(String opus_name) {
		this.opus_name = opus_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getDiscuss_msg() {
		return discuss_msg;
	}
	public void setDiscuss_msg(String discuss_msg) {
		this.discuss_msg = discuss_msg;
	}
	public String getDiscuss_date() {
		return discuss_date;
	}
	public void setDiscuss_date(String discuss_date) {
		this.discuss_date = discuss_date;
	}
	
	
}
