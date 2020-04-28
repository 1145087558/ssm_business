package com.qst.backstagecontroller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qst.entity.Opus;
import com.qst.entity.Order;
import com.qst.entity.User;
import com.qst.service.OpusService;
import com.qst.service.UserService;

@Controller
@RequestMapping("/backstage")
public class CartBackstageController {

	@Autowired
	OpusService opusService;
	@Autowired
	UserService userService;

	/**
	 * 订单管理，如果是普通用户只能查看自己作品的订单，如果是管理员则可以看到所有的订单
	 */
	@RequestMapping("getCartAll.form")
	public String getOpusAll(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			if (user.getStatus() == 0) {
				List<Opus> opusList = opusService.seekOpusByUserId(user.getId());
				List<Order> orderList = new LinkedList<Order>();
				for (Opus opus : opusList) {
					List<Order> orders = opusService.seekOrderByOpus(opus.getId());
					for (int i = 0; i < orders.size(); i++)
						orderList.add(orders.get(i));
				}
				request.setAttribute("orderList", orderList);
			} else {
				List<Order> orderList = opusService.seekOrderAll();
				request.setAttribute("orderList", orderList);
			}
		}
		return "backstage/order-list";
	}
	
	/**
	 * 订单管理，获取已售作品
	 */
	@RequestMapping("getSoldOpus.form")
	public String getSoldOpus(HttpServletRequest request) {
		List<Opus> opusList = opusService.getSoldOpus();
		request.setAttribute("opusList", opusList);
		return "backstage/order-brand";
	}

	/**
	 * 订单删除，根据订单id进行删除
	 */
	@RequestMapping("deletOrder.form")
	public void deletOrder(int id,HttpServletRequest request) {
		opusService.deleteOrder(id);
	}

	/**
	 * 统计购买数排名前十的作品
	 */
	@RequestMapping("getOrderLike.form")
	@ResponseBody
	public List<Opus> getOrderLike() {
		return opusService.getOrderLike();
	}
	
	/**
	 * 获取催促订单
	 */
	@RequestMapping("getPrompt.form")
	public String getPrompt(Model model) {
		
		List<Order> orderList = opusService.getPrompt();
		model.addAttribute("orderList", orderList);
		return "backstage/order-prompt";
	}
	
	/**
	 * 发货作品
	 */
	@RequestMapping("doDelivery.form")
	public void doDelivery(String order_number) {
		
		Order order = opusService.seekOrderByNumber(order_number);
		order.setPrompt(0);
		order.setStatus("已发货");
		opusService.updateOrder(order);
	}
	
	/**
	 * 获取所有发货作品
	 */
	@RequestMapping("getDelivery.form")
	public String getDelivery(Model model) {
		
		List<Order> orderList = opusService.getDelivery();
		model.addAttribute("orderList", orderList);
		
		return "backstage/order-delivery";
	}
	
	@RequestMapping("searchCartAll.form")
	public String searchCartAll(HttpServletRequest request,Order order) {
		
		if("全部".equals(order.getOrder_type())){
			order.setOrder_type(null);
		}else if("".equals(order.getOrder_number())){
			order.setOrder_number(null);
		}else if("".equals(order.getOpus_name())){
			order.setOpus_name(null);
		}
		
		List<Order> orderList = opusService.searchCartAll();
		request.setAttribute("orderList", orderList);
		return "backstage/order-list";
	}
}
