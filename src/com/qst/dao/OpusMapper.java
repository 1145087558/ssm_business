package com.qst.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qst.entity.Cart;
import com.qst.entity.Discuss;
import com.qst.entity.Evaluate;
import com.qst.entity.Opus;
import com.qst.entity.OpusType;
import com.qst.entity.Order;
import com.qst.entity.Tipic;

public interface OpusMapper {

	public List<Opus> liketoplist();

	public List<Opus> getOrderLike();

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

	public Discuss getDiscussMsg(int id);

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

	public void updateOrder(Order order);

	public Order seekOrderByNumber(String order_number);

	public void deleteOrder(int id);

	public List<Discuss> seekDiscussMsgAll();

	public void deleteDiscussmsg(int id);

	public List<Opus> priceSort();

	public List<Opus> heatSort();

	public List<Opus> timesSort();

	public List<Opus> timeSort();

	public void updateDiscuss(Discuss discuss);

	public List<Opus> getAuthorLike();

	public List<Order> getOrderByStatus(String status);

	public void addBrowse(String type, int userId);

	public Opus countBrowse(int id);

	public List<Opus> getScreen(@Param(value = "tipic") String tipic, @Param(value = "minprice") String minprice,
			@Param(value = "maxprice") String maxprice);

	void addCollet(int opusId, Integer userId);

	void deleteCollet(int opusId, Integer userId);

	Integer checkCollet(int opusId, Integer userId);
	
	List<Opus> getCollet(int userId);

	public List<Tipic> getTipicAll();

	public void addTipic(Tipic tipic);

	public void deleteTipic(int id);

	public List<Opus> getSoldOpus();

	public List<OpusType> getTypeAll();

	public void addType(OpusType opusType);

	public void deleteType(int id);

	public List<Order> getPrompt();

	public List<Order> getDelivery();

	public Evaluate seekEvaluate(Integer id);

	public List<Order> getDeliveryByUser(Integer id);

	public Order getOrderById(Integer order_id);

	public void addEvaluate(Evaluate evalute);

	public List<Order> searchCartAll(@Param(value = "order_number") String order_number, @Param(value = "order_type") String order_type,
			@Param(value = "user_name") String user_name);

	public List<Opus> searchOpus(@Param(value = "opus_name")String opus_name, @Param(value = "opus_tipic")String opus_tipic,
			@Param(value = "status")Integer status);

	public List<Discuss> serachDiscussmsg(@Param(value = "opus_name")String opus_name, @Param(value = "user_name") String user_name);
}
