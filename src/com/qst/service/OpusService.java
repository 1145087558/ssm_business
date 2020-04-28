package com.qst.service;

import java.util.List;

import com.qst.entity.Cart;
import com.qst.entity.Discuss;
import com.qst.entity.Evaluate;
import com.qst.entity.Opus;
import com.qst.entity.OpusType;
import com.qst.entity.Order;
import com.qst.entity.Tipic;

public interface OpusService {
	public List<Opus> liketoplist();
	
	public List<Opus> findAll();

	public List<Opus> recommend(int id);
	
	public Opus opusDetail(int id);

	public void dealWithLikeTimes(Opus opus);

	public List<Opus> opusSearch(String search);
	
	public List<Opus> opusSearchByTipic(String search);

	public void addCart(Cart cart);

	public List<Cart> displayCart(int id);

	public void clearCart(int id);

	public List<Opus> priceSort();

	public List<Opus> heatSort();
	
	public List<Opus> timesSort();

	public void deleteCartOpus(int id);

	public void addDiscuss(Discuss discuss);

	public List<Discuss> seekDiscussMsg(int id);

	public void addOrder(Order order);
	
	public void updateOrder(Order order);

	public List<Order> seekOrder(int id);
	
	public Order seekOrderByNumber(String order_number);

	public void upload(Opus opus);

	public List<Opus> seekOpusByUserId(Integer id);

	public void modifyOpusByStatus(int status, int id);

	public void updateOpus(Opus opus);

	public List<Opus> getOpusAll();

	public void deleteOpus(int id);

	public List<Opus> getOpusByStatus(int status);

	public List<Order> seekOrderByOpus(int id);

	public List<Order> seekOrderAll();

	public void deleteOrder(int id);

	public List<Discuss> seekDiscussMsgAll();

	public void deleteDiscussmsg(int id);

	public List<Opus> timeSort();

	public Discuss getDiscussMsg(int id);

	public void updateDiscussMsg(Discuss discuss);

	public List<Opus> getOrderLike();

	public List<Opus> getAuthorLike();

	public List<Order> getOrderByStatus(String status);
	
	public void addBrowse(String type,int userId);

	public List<Opus> getScreen(String tipic, String minprice, String maxprice);

	void addCollet(int opusId, Integer id);

	void deleteCollet(int opusId, Integer id);

	String checkCollet(int opusId, Integer id);
	
	List<Opus> getCollet(int userId);

	public List<Tipic> getTipicAll();

	public void addTipic(Tipic tipic);

	public void deleteTipic(int id);

	public List<Opus> getSoldOpus();

	public List<OpusType> getTypeAll();

	public void addType(OpusType opusType);

	public void deleteType(int id);

	public void prompt(String out_trade_no);

	public List<Order> getPrompt();

	public List<Order> getDelivery();

	public Evaluate seekEvaluate(Integer id);

	public List<Order> getDeliveryByUser(Integer id);

	public Order getOrderById(Integer order_id);

	public void addEvaluate(Evaluate evalute);
	
}
