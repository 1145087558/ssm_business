package com.qst.dao;

import java.util.List;

import com.qst.entity.Cart;
import com.qst.entity.Discuss;
import com.qst.entity.Opus;
import com.qst.entity.Order;

public interface OpusMapper {
	public List<Opus> liketoplist();
	public List<Opus> findAll();

	public Opus opusDetail(int id);

	public void dealWithLikeTimes(Opus opus);

	public List<Opus> opusSearch(String search);
	
	public List<Opus> opusSearchByName(String search);
	
	public List<Opus> opusSearchByTipic(String search);

	public void addCart(Cart cart);

	public List<Cart> displayCart(int id);

	public void clearCart(int id);

	public void deleteCartOpus(int id);

	public void addDiscuss(Discuss discuss);

	public List<Discuss> seekDiscussMsg(int id);

	public void addOrder(Order order);

	public List<Order> seekOrder(int id);

	public void upload(Opus opus);

	public List<Opus> seekOpusByUserId(Integer id);

	public void modifyOpusByStatus(int status, int id);

	public void updateOpus(Opus opus);

	public List<Opus> getOpusAll();

	public void deleteOpus(int id);

	public List<Opus> getOpusByStatus(int status);

	public List<Order> seekOrderByOpus(int id);

	public List<Order> seekOrderAll();

	public void deletOrder(int id);

	public List<Discuss> seekDiscussMsgAll();

	public void deleteDiscussmsg(int id);

	public List<Opus> priceSort();

	public List<Opus> heatSort();

	public List<Opus> timesSort();

	public List<Opus> timeSort();

	


}
