package com.qst.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.qst.entity.User;
import com.qst.service.UserService;
import com.qst.util.AlipayConfig;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	// 注册
	@RequestMapping("register.form")
	public String register(User user) {
		System.out.println("接收到的手机号和密码：" + user.getTel() + "*****" + user.getPwd());
		user.setName(user.getTel());
		userService.userRegister(user);
		return "login";
	}

	// 登录
	@RequestMapping("login.form")
	public String login(User user, HttpServletRequest request) {
		System.out.println("手机号：" + user.getTel() + "密码：" + user.getPwd());
		user = userService.userLogin(user);
		if (user == null) {
			request.setAttribute("error", "error");
			return "login";
		}
		System.out.println("用户ID：" + user.getId());
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		return "redirect:findAll.form";
	}

	// 退出
	@RequestMapping("logout.form")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();

		return "redirect:findAll.form";
	}

	// 余额充值
	@RequestMapping("pay.form")
	public void invest(double balance, HttpServletRequest request, HttpServletResponse response) throws IOException {

		String body = "余额充值";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderSn = simpleDateFormat.format(Calendar.getInstance().getTime());
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl("http://localhost:8080/ssm_business/doWallerPaySuccess");
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderSn + "\"," + "\"total_amount\":\"" + balance + "\","
				+ "\"subject\":\"" + "商品订单支付" + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		AlipayTradePagePayResponse alipayResponse = null;
		try {
			alipayResponse = alipayClient.pageExecute(alipayRequest);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("orderSn", orderSn);
		request.getSession().setAttribute("totalprice", balance);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(alipayResponse.getBody());

	}

	// 支付宝支付成功
	@RequestMapping("doWallerPaySuccess")
	public String doWallerPaySuccess(HttpServletRequest request) {

		//String orderSn = (String) request.getSession().getAttribute("orderSn");
		double balance = (double) request.getSession().getAttribute("totalprice");

		//int id = (int) request.getSession().getAttribute("orderIds");
		User user = (User) request.getSession().getAttribute("user");
		double balan = user.getBalance() + balance;
		user.setBalance(balan);
		userService.modifyBalance(user);

		request.getSession().setAttribute("user", user);
		return "redirect:findAll.form";
	}

	// 更改个人信息
	@RequestMapping("person.form")
	public String personMsg(String name, String tel, String address, String synopsis, HttpServletRequest request) {
		System.out.println("个人信息：" + synopsis);
		User user = (User) request.getSession().getAttribute("user");
		user.setName(name);
		user.setAddress(address);
		user.setSynopsis(synopsis);
		userService.modifyPersonMsg(user);

		return "redirect:findAll.form";
	}
	
}
