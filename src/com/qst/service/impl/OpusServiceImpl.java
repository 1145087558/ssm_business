package com.qst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qst.dao.OpusMapper;
import com.qst.entity.Cart;
import com.qst.entity.Discuss;
import com.qst.entity.Opus;
import com.qst.entity.Order;
import com.qst.service.OpusService;
@Service
public class OpusServiceImpl implements OpusService{

	@Autowired
	OpusMapper opusMapper;
	@Override
	public List<Opus> findAll() {
		List<Opus> opusList=opusMapper.findAll();
		return opusList;
	}
	@Override
	public Opus opusDetail(int id) {
		Opus opus =new Opus();
		opus=opusMapper.opusDetail(id);
		return opus;
	}
	@Override
	public void dealWithLikeTimes(Opus opus) {
		opusMapper.dealWithLikeTimes(opus);
		
	}
	@Override
	public List<Opus> opusSearchByTipic(String search) {
		//先判断关键字是否为画类型
		List<Opus> opuslist= opusMapper.opusSearchByTipic(search);
		return opuslist;
	}
	
	@Override
	public List<Opus> opusSearch(String search) {
		//先判断关键字是否为画类型
		List<Opus> opuslist=opusMapper.opusSearchByTipic(search);
		if(opuslist.size()==0){
			//判断关键字是否为画名称
			opuslist= opusMapper.opusSearchByName(search);
			if(opuslist.size()==0){
				//判断关键字是否为作家
				opuslist= opusMapper.opusSearch(search);
			}
		}
		return opuslist;
	}
	
	@Override
	public void addCart(Cart cart) {
		opusMapper.addCart(cart);
		
	}
	@Override
	public List<Cart> displayCart(int id) {
		List<Cart> cartList=opusMapper.displayCart(id);
		return cartList;
	}
	@Override
	public void clearCart(int id) {
		opusMapper.clearCart(id);
		
	}
				
				
	
	
	@Override
	public void deleteCartOpus(int id) {
		opusMapper.deleteCartOpus(id);
		
	}
	@Override
	public void addDiscuss(Discuss discuss) {
		opusMapper.addDiscuss(discuss);
		//System.out.println("service层的ID："+discuss.getOpus_id());
	}
	@Override
	public List<Discuss> seekDiscussMsg(int id) {
		List<Discuss> disList=opusMapper.seekDiscussMsg(id);
		return disList;
	}
	@Override
	public void addOrder(Order order) {
		opusMapper.addOrder(order);
		
	}
	@Override
	public List<Order> seekOrder(int id) {
		List<Order> orderList=opusMapper.seekOrder(id);
		return orderList;
	}
	@Override
	public void upload(Opus opus) {
		opusMapper.upload(opus);
		
	}
	@Override
	public List<Opus> seekOpusByUserId(Integer id) {
		
		return opusMapper.seekOpusByUserId(id);
	}
	@Override
	public void modifyOpusByStatus(int status, int id) {
		opusMapper.modifyOpusByStatus(status,id);
	}
	@Override
	public void updateOpus(Opus opus) {
		opusMapper.updateOpus(opus);
		
	}
	@Override
	public List<Opus> getOpusAll() {
		// TODO Auto-generated method stub
		return opusMapper.getOpusAll();
	}
	@Override
	public void deleteOpus(int id) {
		opusMapper.deleteOpus(id);
		
	}
	@Override
	public List<Opus> getOpusByStatus(int status) {
		
		return opusMapper.getOpusByStatus(status);
	}
	@Override
	public List<Order> seekOrderByOpus(int id) {
		
		return opusMapper.seekOrderByOpus(id);
	}
	@Override
	public List<Order> seekOrderAll() {
		
		return opusMapper.seekOrderAll();
	}
	@Override
	public void deleteOrder(int id) {
		opusMapper.deleteOrder(id);
		
	}
	@Override
	public List<Discuss> seekDiscussMsgAll() {
		
		return opusMapper.seekDiscussMsgAll();
	}
	@Override
	public void deleteDiscussmsg(int id) {
		
		opusMapper.deleteDiscussmsg(id);
	}
	@Override
	public List<Opus> priceSort() {
		
		return opusMapper.priceSort();
	}
	@Override
	public List<Opus> heatSort() {
		// TODO Auto-generated method stub
		return opusMapper.heatSort();
	}
	@Override
	public List<Opus> timesSort() {
		// TODO Auto-generated method stub
		return opusMapper.timesSort();
	}
	@Override
	public List<Opus> timeSort() {
		
		return opusMapper.timeSort();
	}
	@Override
	public List<Opus> liketoplist() {
		// TODO Auto-generated method stub
		return opusMapper.liketoplist();
	}
	@Override
	public Order seekOrderByNumber(String order_number) {
		
		return opusMapper.seekOrderByNumber(order_number);
	}
	@Override
	public void updateOrder(Order order) {
		opusMapper.updateOrder(order);
		
	}
	@Override
	public Discuss getDiscussMsg(int id) {
		
		return opusMapper.getDiscussMsg(id);
	}
	@Override
	public void updateDiscussMsg(Discuss discuss) {
		
		opusMapper.updateDiscuss(discuss);
	}
	@Override
	public List<Opus> getOrderLike() {
		
		return opusMapper.getOrderLike();
	}
}
