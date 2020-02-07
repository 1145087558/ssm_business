package com.qst.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.qst.entity.User;
import com.qst.entity.UserAddress;
import com.qst.service.UserService;
import com.qst.util.AlipayConfig;
import com.qst.util.RegexMatche;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JavaMailSenderImpl mailSender;

	// 注册
	@RequestMapping("register.form")
	public String register(User user) {
		System.out.println("接收到的手机号和密码：" + user.getTel() + "*****" + user.getPwd());
		user.setName(user.getTel());
		userService.userRegister(user);
		return "login";
	}

	// 邮箱注册
	@RequestMapping("emailRegister.form")
	public String emailRegister(User user) {

		user.setName(user.getTel());
		userService.addUser(user);
		sendEmail(user.getEmail());
		return "login";
	}

	// 手机号码注册
	@RequestMapping("telRegister.form")
	public String telRegister(User user, HttpServletRequest request) {
		String code = request.getParameter("code");
		String phoneCode = (String) request.getSession().getAttribute("phoneCode");
		if (code.equals(phoneCode)) {
			userService.addUser(user);
			return "login";
		} else {
			request.setAttribute("error", "验证码错误");
			return "register";
		}
	}

	// 登录
	@RequestMapping("login.form")
	public String login(String name, String pwd, HttpServletRequest request) {
		System.out.println("账号：" + name + "密码：" + pwd);

		User user = new User();
		user.setPwd(pwd);
		if (RegexMatche.isPhone(name)) {
			System.out.println("手机号码登录");
			user.setTel(name);
			user = userService.userLogin(user);
		} else if (RegexMatche.isEmail(name)) {
			System.out.println("邮箱登录");
			user.setEmail(name);
			user = userService.userLogin(user);
		} else {
			System.out.println("用户名登录");
			user.setName(name);
			user = userService.userLogin(user);
		}

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

		// String orderSn = (String)
		// request.getSession().getAttribute("orderSn");
		double balance = (double) request.getSession().getAttribute("totalprice");

		// int id = (int) request.getSession().getAttribute("orderIds");
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

	// 发送短信验证
	@RequestMapping("sendPhoneCode.form")
	public void sendPhoneCode(HttpServletRequest request, String phone) throws HttpException, IOException {

		String phoneCode = smsCode();
		System.out.println(phoneCode);
		request.getSession().setAttribute("phoneCode", phoneCode);
		/*
		 * HttpClient client = new HttpClient(); PostMethod post = new
		 * PostMethod("http://utf8.api.smschinese.cn");
		 * post.addRequestHeader("Content-Type",
		 * "application/x-www-form-urlencoded;charset=UTF-8"); NameValuePair[] data = {
		 * new NameValuePair("Uid", "a1145087558"), new NameValuePair("Key",
		 * "d41d8cd98f00b204e980"), new NameValuePair("smsMob", phone), new
		 * NameValuePair("smsText", "【墨韵书院平台】尊敬的用户，您好，您的验证码为：" + phoneCode +
		 * "，若非本人操作，请忽略此短信。") }; post.setRequestBody(data);
		 * 
		 * client.executeMethod(post); Header[] headers = post.getResponseHeaders(); int
		 * statusCode = post.getStatusCode(); System.out.println("statusCode:" +
		 * statusCode); for (Header h : headers) { System.out.println(h.toString()); }
		 * String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
		 * System.out.println(result);
		 * 
		 * post.releaseConnection();
		 */
	}

	// 生成短信验证随机数
	public String smsCode() {
		String random = (int) ((Math.random() * 9 + 1) * 100000) + "";
		return random;
	}

	public void sendEmail(String email) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper message;
		try {
			message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			message.setFrom("1677892776@qq.com", "墨韵书院客服");
			message.setTo(email);
			message.setSubject("墨韵书院平台");
			message.setText("用户:<br> 你好!<br>欢迎加入墨韵书院平台,您可以使用该平台进行买卖!", true);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		mailSender.send(mimeMessage);
	}

	// 检查密码是否被注册
	@RequestMapping("checkPassword.form")
	public void checkPassword(HttpServletResponse response, HttpServletRequest req, String pwd) throws IOException {
		boolean confirm;
		User user = (User) req.getSession().getAttribute("user");
		Integer id = user.getId();
		if (userService.getPasswordByid(pwd, id)) {
			confirm = true;
			String confirmJson = JSON.toJSONString(confirm);
			response.getWriter().write(confirmJson);
		} else {
			confirm = false;
			String confirmJson = JSON.toJSONString(confirm);
			response.getWriter().write(confirmJson);
		}
	}

	// 检查邮箱是否被注册
	@RequestMapping("checkEmail.form")
	public void checkEmail(HttpServletResponse response, String email) throws IOException {
		boolean confirm;
		if (userService.checkEmail(email)) {
			confirm = false;
			String confirmJson = JSON.toJSONString(confirm);
			response.getWriter().write(confirmJson);
		} else {
			confirm = true;
			String confirmJson = JSON.toJSONString(confirm);
			response.getWriter().write(confirmJson);
		}
	}

	// 检查手机号是否被注册
	@RequestMapping("checkPhone.form")
	public void checkPhone(HttpServletResponse response, String tel) throws IOException {
		boolean confirm;
		if (userService.checkPhone(tel)) {
			confirm = false;
			String confirmJson = JSON.toJSONString(confirm);
			response.getWriter().write(confirmJson);
		} else {
			confirm = true;
			String confirmJson = JSON.toJSONString(confirm);
			response.getWriter().write(confirmJson);
		}
	}

	// 获取用户地址
	@RequestMapping("getAddress.form")
	@ResponseBody
	public List<UserAddress> getAddress(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		// 传递json数据
		return userService.getUserAddress(user.getId());

	}

	// 修改用户地址
	@RequestMapping("modifyAddress.form")
	public String modifyAddress(UserAddress userAddress) {
		userService.modifyAddress(userAddress);
		return "address_manage";
	}

	// 添加用户地址
	@RequestMapping("addUserAddress.form")
	public String addUserAddress(UserAddress userAddress, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		userAddress.setUser_id(user.getId());
		userService.addUserAddress(userAddress);
		return "address_manage";
	}

	// 删除用户地址
	@RequestMapping("deleteAddress.form")
	public void deleteAddress(int id) {
		userService.deleteAddress(id);
	}

	// 修改密码
	@RequestMapping("modifyPassword.form")
	public String modifyPassword(String oldpassword, String newpassword, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		userService.modifyPassword(user.getId(), newpassword);

		return "personCenter";
	}

	// 修改用户信息
	@RequestMapping("modifyUser.form")
	public String modifyUser(User user, HttpServletRequest request) {

		userService.modifyUser(user);
		user = userService.getUserById(user.getId());
		request.getSession().setAttribute("user", user);
		return "personCenter";
	}

}
