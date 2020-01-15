package com.qst.entity;

public class Opus {

	private int id;
	private String opus_name;//作品标题
	private String author_name;//作者
	private String opus_image;//图片存储位置
	private String opus_synopsis;//简介
	private String opus_create_time;//创建时间
	private Integer opus_like_times;//点赞数
	private Integer opus_heat;//热度
	private double opus_price;//售卖价格
	private String opus_tipic;
	private Integer status;
	private Integer user_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getOpus_image() {
		return opus_image;
	}
	public void setOpus_image(String opus_image) {
		this.opus_image = opus_image;
	}
	public String getOpus_synopsis() {
		return opus_synopsis;
	}
	public void setOpus_synopsis(String opus_synopsis) {
		this.opus_synopsis = opus_synopsis;
	}
	public String getOpus_create_time() {
		return opus_create_time;
	}
	public void setOpus_create_time(String opus_create_time) {
		this.opus_create_time = opus_create_time;
	}
	public Integer getOpus_like_times() {
		return opus_like_times;
	}
	public void setOpus_like_times(Integer opus_like_times) {
		this.opus_like_times = opus_like_times;
	}
	public Integer getOpus_heat() {
		return opus_heat;
	}
	public void setOpus_heat(Integer opus_heat) {
		this.opus_heat = opus_heat;
	}
	public double getOpus_price() {
		return opus_price;
	}
	public void setOpus_price(double opus_price) {
		this.opus_price = opus_price;
	}
	public String getOpus_tipic() {
		return opus_tipic;
	}
	public void setOpus_tipic(String opus_tipic) {
		this.opus_tipic = opus_tipic;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
}
