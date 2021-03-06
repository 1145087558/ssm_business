package com.qst.entity;

public class Order {

	private Integer id;
	private int user_id;
	private int opus_id;
	private Integer address_id;
	private String  order_number;
	private String  order_type;
	private String user_name;
	private String opus_name;
	private String user_address;
	private double opus_price;
	private String order_date;
	private String status;
	private Integer prompt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getOpus_name() {
		return opus_name;
	}
	public void setOpus_name(String opus_name) {
		this.opus_name = opus_name;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public double getOpus_price() {
		return opus_price;
	}
	public void setOpus_price(double opus_price) {
		this.opus_price = opus_price;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getPrompt() {
		return prompt;
	}
	public void setPrompt(Integer prompt) {
		this.prompt = prompt;
	}
	
	
}
