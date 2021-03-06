package com.qst.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qst.dao.OpusMapper;
import com.qst.entity.Cart;
import com.qst.entity.Discuss;
import com.qst.entity.Evaluate;
import com.qst.entity.Opus;
import com.qst.entity.OpusType;
import com.qst.entity.Order;
import com.qst.entity.Tipic;
import com.qst.service.OpusService;

@Service
public class OpusServiceImpl implements OpusService {

	@Autowired
	OpusMapper opusMapper;

	@Override
	public List<Opus> findAll() {
		List<Opus> opusList = opusMapper.findAll();
		return opusList;
	}

	@Override
	public Opus opusDetail(int id) {
		Opus opus = new Opus();
		opus = opusMapper.opusDetail(id);
		return opus;
	}

	@Override
	public void dealWithLikeTimes(Opus opus) {
		opusMapper.dealWithLikeTimes(opus);

	}

	@Override
	public List<Opus> opusSearchByTipic(String search) {
		// 先判断关键字是否为画类型
		List<Opus> opuslist = opusMapper.opusSearchByTipic(search);
		return opuslist;
	}

	@Override
	public List<Opus> opusSearch(String search) {
		// 先判断关键字是否为画类型
		List<Opus> opuslist = opusMapper.opusSearchByTipic(search);
		if (opuslist.size() == 0) {
			// 判断关键字是否为画名称
			opuslist = opusMapper.opusSearchByName(search);
			if (opuslist.size() == 0) {
				// 判断关键字是否为作家
				opuslist = opusMapper.opusSearch(search);
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
		List<Cart> cartList = opusMapper.displayCart(id);
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
		// System.out.println("service层的ID："+discuss.getOpus_id());
	}

	@Override
	public List<Discuss> seekDiscussMsg(int id) {
		List<Discuss> disList = opusMapper.seekDiscussMsg(id);
		return disList;
	}

	@Override
	public void addOrder(Order order) {
		opusMapper.addOrder(order);

	}

	@Override
	public List<Order> seekOrder(int id) {
		List<Order> orderList = opusMapper.seekOrder(id);
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
		opusMapper.modifyOpusByStatus(status, id);
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

	@Override
	public List<Opus> getAuthorLike() {
		
		return opusMapper.getAuthorLike();
	}

	@Override
	public List<Order> getOrderByStatus(String status) {
		
		return opusMapper.getOrderByStatus(status);
	}

	@Override
	public void addBrowse(String type, int userId) {
		opusMapper.addBrowse(type, userId);
		
	}

	@Override
	public List<Opus> recommend(int id) {
		
		Opus opus= opusMapper.countBrowse(id);
		if(opus == null){
			return opusMapper.findAll();
		}
		return opusMapper.opusSearchByTipic(opus.getOpus_tipic());
	}

	@Override
	public List<Opus> getScreen(String tipic, String minprice, String maxprice) {
		
		return opusMapper.getScreen(tipic, minprice, maxprice);
	}
	
	@Override
	public void addCollet(int opusId, Integer userId) {
		
		opusMapper.addCollet(opusId,userId);
	}
	
	@Override
	public void deleteCollet(int opusId, Integer userId) {
		
		opusMapper.deleteCollet(opusId,userId);
	}
	

	@Override
	public String checkCollet(int opusId, Integer userId) {
		
		Integer i = opusMapper.checkCollet(opusId,userId);
		if(i!=null){
			return "success";
		}
		return "fail";
	}

	@Override
	public List<Opus> getCollet(int userId) {
		
		return opusMapper.getCollet(userId);
	}

	@Override
	public List<Tipic> getTipicAll() {
		
		return opusMapper.getTipicAll();
	}

	@Override
	public void addTipic(Tipic tipic) {
		opusMapper.addTipic(tipic);
	}

	@Override
	public void deleteTipic(int id) {
		
		opusMapper.deleteTipic(id);
	}

	@Override
	public List<Opus> getSoldOpus() {
		
		return opusMapper.getSoldOpus();
	}

	@Override
	public List<OpusType> getTypeAll() {
		
		return opusMapper.getTypeAll();
	}

	@Override
	public void addType(OpusType opusType) {
		
		opusMapper.addType(opusType);
	}

	@Override
	public void deleteType(int id) {
		
		opusMapper.deleteType(id);
	}

	@Override
	public void prompt(String out_trade_no) {
		Order order = opusMapper.seekOrderByNumber(out_trade_no);
		
		order.setPrompt(order.getPrompt()+1);
		opusMapper.updateOrder(order);
	}

	@Override
	public List<Order> getPrompt() {
		
		return opusMapper.getPrompt();
	}

	@Override
	public List<Order> getDelivery() {
		
		return opusMapper.getDelivery();
	}

	@Override
	public Evaluate seekEvaluate(Integer id) {
		
		return opusMapper.seekEvaluate(id);
	}

	@Override
	public List<Order> getDeliveryByUser(Integer id) {
		
		return opusMapper.getDeliveryByUser(id);
	}

	@Override
	public Order getOrderById(Integer order_id) {
		
		return opusMapper.getOrderById(order_id);
	}

	@Override
	public void addEvaluate(Evaluate evalute) {
		opusMapper.addEvaluate(evalute);
		
	}

	@Override
	public List<Order> searchCartAll(String order_number, String order_type,String user_name) {
		
		return opusMapper.searchCartAll(order_number, order_type, user_name);
	}

	@Override
	public List<Opus> searchOpus(Opus opus) {
		
		return opusMapper.searchOpus(opus.getOpus_name(),opus.getOpus_tipic(),opus.getStatus());
	}

	@Override
	public List<Discuss> serachDiscussmsg(Discuss discuss) {
		
		return opusMapper.serachDiscussmsg(discuss.getOpus_name(),discuss.getUser_name());
	}
	
	
}
