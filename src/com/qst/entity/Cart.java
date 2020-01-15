package com.qst.entity;

public class Cart {

	private Integer id;
	private int opus_id;
	private int user_id;
	private String opus_image;
	private String opus_name;
	private String author_name;
	private double opus_price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOpus_id() {
		return opus_id;
	}
	public void setOpus_id(int opus_id) {
		this.opus_id = opus_id;
	}
	public String getOpus_image() {
		return opus_image;
	}
	public void setOpus_image(String opus_image) {
		this.opus_image = opus_image;
	}
	public String getOpus_name() {
		return opus_name;
	}
	public void setOpus_name(String opus_name) {
		this.opus_name = opus_name;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public double getOpus_price() {
		return opus_price;
	}
	public void setOpus_price(double opus_price) {
		this.opus_price = opus_price;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
