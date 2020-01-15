package com.qst.backstagecontroller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

	// 订单管理
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

	// 订单删除
	@RequestMapping("deletOrder.form")
	public void deletOrder(int id,HttpServletRequest request) {
		opusService.deletOrder(id);
	}

}
