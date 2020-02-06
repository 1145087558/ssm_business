package com.qst.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import xyz.peter.UpLoadImageBed;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.qst.entity.Cart;
import com.qst.entity.Discuss;
import com.qst.entity.Opus;
import com.qst.entity.Order;
import com.qst.entity.User;
import com.qst.entity.UserAddress;
import com.qst.service.OpusService;
import com.qst.service.UserService;
import com.qst.util.AlipayConfig;

@Controller
public class OpusController {

	@Autowired
	OpusService opusService;
	@Autowired
	UserService userService;
	
	
	// 展示首页作品
	@RequestMapping("findAll.form")
	public String findAll(HttpServletRequest request) {
		List<Opus> opusList = opusService.findAll();
		request.setAttribute("opusList", opusList);
		return "homepage";

	}

	// 作品详情信息
	@RequestMapping("opusDetail.form")
	public String opusDetail(int id, HttpServletRequest request) {
		Opus opus = new Opus();
		List<Discuss> disList = opusService.seekDiscussMsg(id);
		System.out.println("要展示的作品详细信息的id:" + id);
		opus = opusService.opusDetail(id);
		request.setAttribute("disList", disList);
		// System.out.println("第一条评论："+disList.get(0).getDiscuss_msg());
		// System.out.println(disList.get(0).getDiscuss_date());
		request.setAttribute("opus", opus);
		return "opusDetail";
	}

	// 作品点赞处理
	@RequestMapping("like_times.form")
	public String likeTimes(int id, int like_times) {
		System.out.println("Id:" + id);
		System.out.println("点赞数：" + like_times);
		Opus opus = new Opus();
		opus.setId(id);
		opus.setOpus_like_times(like_times);
		opusService.dealWithLikeTimes(opus);
		return "index";
	}

	// 作品搜索（搜索作家名字）
	@RequestMapping("search.form")
	public String searchOpus(String search, HttpServletRequest request) {
		System.out.println("接收到的关键字：" + search);
		List<Opus> opusList = opusService.opusSearch(search);
		request.setAttribute("opusList", opusList);
		return "homepage";
	}

	// 加入购物车
	@RequestMapping("cart.form")
	public ModelAndView cart(int id, HttpServletRequest request) {
		Opus opus = opusService.opusDetail(id);
		User user = (User) request.getSession().getAttribute("user");
		System.out.println("手机号：" + user.getTel());
		Cart cart = new Cart();
		cart.setOpus_id(opus.getId());
		cart.setUser_id(user.getId());
		cart.setOpus_image(opus.getOpus_image());
		cart.setAuthor_name(opus.getAuthor_name());
		cart.setOpus_name(opus.getOpus_name());
		cart.setOpus_price(opus.getOpus_price());
		System.out.println(cart);
		opusService.addCart(cart);
		System.out.println("id:::" + cart.getOpus_id());
		RedirectView rv = new RedirectView("displayCart.form");
		return new ModelAndView(rv);
	}

	// 显示购物车商品
	@RequestMapping("displayCart.form")
	public String displayCart(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<Cart> cartList = opusService.displayCart(user.getId());
		List<UserAddress> address = userService.getUserAddress(user.getId());
		request.setAttribute("cartList", cartList);
		request.setAttribute("addresss", address);
		System.out.println(address.get(0));
		System.out.println("用户输出L:" + user);
		for (Cart c : cartList)
			System.out.println(c.getOpus_id());

		return "cart";

	}

	// 清空购物车
	@RequestMapping("clearCart.form")
	public ModelAndView clearCart(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		opusService.clearCart(user.getId());
		RedirectView rv = new RedirectView("displayCart.form");
		return new ModelAndView(rv);

	}

	// 删除购物车中的某一件作品
	@RequestMapping("delete.form")
	public ModelAndView deleteCartOpus(int id) {
		opusService.deleteCartOpus(id);
		RedirectView rv = new RedirectView("displayCart.form");
		return new ModelAndView(rv);
	}

	// 按最新时间排序
	@RequestMapping("timeSort.form")
	public String timeSort(HttpServletRequest request) {
		List<Opus> opusList = opusService.timeSort();
		request.setAttribute("opusList", opusList);
		return "homepage";

	}

	// 按价格排序
	@RequestMapping("priceSort.form")
	public String priceSort(HttpServletRequest request) {
		List<Opus> opusList = opusService.priceSort();
		request.setAttribute("opusList", opusList);
		return "homepage";
	}
	
	// 按点赞数排序
	@RequestMapping("timesSort.form")
	public String timesSort(HttpServletRequest request) {
		List<Opus> opusList = opusService.timesSort();
		request.setAttribute("opusList", opusList);
		return "homepage";

	}
	// 按热度排序
	@RequestMapping("heatSort.form")
	public String heatSort(HttpServletRequest request) {
		List<Opus> opusList = opusService.heatSort();
		request.setAttribute("opusList", opusList);
		return "homepage";
	}
	// 按分类查询作品
	@RequestMapping("seek.form")
	public String classifySeek(String seek,HttpServletRequest request) {
		System.out.println("controller层接收到的关键字：" + seek);
		List<Opus> opusList = opusService.opusSearchByTipic(seek);
		request.setAttribute("opusList", opusList);
		return "homepage";
	}

	// 处理评论信息
	@RequestMapping("discuss.form")
	public ModelAndView discuss(String discussMsg, int id, String name, HttpServletRequest request) {
		System.out.println("id：" + id + "***********" + name + "**********" + discussMsg);
		User user = (User) request.getSession().getAttribute("user");
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = dateFormat.format(date);
		System.out.println("当前日期：" + nowDate);
		Discuss discuss = new Discuss();
		discuss.setOpus_id(id);
		discuss.setOpus_name(name);
		discuss.setUser_id(user.getId());
		discuss.setUser_name(user.getTel());
		discuss.setDiscuss_msg(discussMsg);
		discuss.setDiscuss_date(nowDate);
		opusService.addDiscuss(discuss);

		RedirectView rv = new RedirectView("findAll.form");
		return new ModelAndView(rv);
	}

	// 处理订单信息
	@RequestMapping("orderWaller.form")
	public void orderDealWith(String[] orderDatas,String[] cartIds, String useraddress,int addressId,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		int length = orderDatas.length;
		String[] ids = new String[length];
		String[] names = new String[length];
		double[] prices = new double[length];
		Integer[] cartId = new Integer[length];
		double totalprice = 0;
		for (int i = 0; i < length; i++) {
			ids[i] = orderDatas[i].substring(orderDatas[i].indexOf("id=") + 3, orderDatas[i].indexOf("&name"));
			names[i] = orderDatas[i].substring(orderDatas[i].indexOf("name=") + 5, orderDatas[i].indexOf("&price"));
			prices[i] = Double
					.parseDouble(orderDatas[i].substring(orderDatas[i].indexOf("price=") + 6, orderDatas[i].length()));
			totalprice += prices[i];
			cartId[i] = Integer.parseInt(cartIds[i].substring(cartIds[i].indexOf("id=") + 3,cartIds[i].length()));
		}
		
		User user = (User) request.getSession().getAttribute("user");
		double balance = user.getBalance() - totalprice;
		if(balance >0){
			System.out.println("购买后的余额：" + balance);
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = dateFormat.format(date);
			for (int i = 0; i < ids.length; i++) {
				Order order = new Order();
				order.setOpus_id(Integer.parseInt(ids[i]));
				order.setOpus_name(names[i]);
				order.setOpus_price(prices[i]);
				order.setUser_id(user.getId());
				order.setUser_name(user.getName());
				order.setUser_address(useraddress);
				order.setAddress_id(addressId);
				order.setOrder_date(nowDate);
				order.setStatus("已支付");
				opusService.addOrder(order);
				opusService.deleteCartOpus(cartId[i]);
			}
			user.setBalance(balance);
			userService.modifyBalance(user);
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("下单成功，可前往订单页面查看");
			
		}else{
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("余额不足");
		}

	}

	// 支付宝支付
	@RequestMapping("order.form")
	public void zifubaoPay(int id, String name, double price, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String body = "购买的商品:" + name;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderSn = simpleDateFormat.format(Calendar.getInstance().getTime());
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl("http://localhost:8080/ssm_business/doPaySuccess");
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderSn + "\"," + "\"total_amount\":\"" + price + "\","
				+ "\"subject\":\"" + "商品订单支付" + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		AlipayTradePagePayResponse alipayResponse = null;
		try {
			alipayResponse = alipayClient.pageExecute(alipayRequest);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("orderSn", orderSn);
		request.getSession().setAttribute("orderIds", id);
		request.getSession().setAttribute("totalprice", price);
		request.getSession().setAttribute("name", name);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(alipayResponse.getBody());
	}

	// 支付宝支付成功
	@RequestMapping("doPaySuccess")
	public String doPaySuccess(HttpServletRequest request) {

		String orderSn = (String) request.getSession().getAttribute("orderSn");
		double price = (double) request.getSession().getAttribute("totalprice");
		String name = (String) request.getSession().getAttribute("name");
		int id = (int) request.getSession().getAttribute("orderIds");
		User user = (User) request.getSession().getAttribute("user");
		Date date = new Date();
		Order order = new Order();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = dateFormat.format(date);
		order.setOpus_id(id);
		order.setOpus_name(name);
		order.setOpus_price(price);
		order.setUser_id(user.getId());
		order.setUser_name(user.getName());
		order.setUser_address(user.getAddress());
		order.setOrder_date(nowDate);
		order.setStatus("已支付");
		opusService.addOrder(order);
		request.getSession().setAttribute("user", user);
		return "redirect:seekOrder.form";
	}

	// 支付宝支付多件
	@RequestMapping("orderPay.form")
	public void zifubaoPay(String[] orderDatas, String[] cartIds,String useraddress,int addressId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int length = orderDatas.length;
		String[] ids = new String[length];
		String[] names = new String[length];
		double[] prices = new double[length];
		Integer[] cartId = new Integer[length];
		double totalprice = 0;
		StringBuffer body = new StringBuffer();
		body.append("商品信息：");
		for (int i = 0; i < length; i++) {
			ids[i] = orderDatas[i].substring(orderDatas[i].indexOf("id=") + 3, orderDatas[i].indexOf("&name"));
			names[i] = orderDatas[i].substring(orderDatas[i].indexOf("name=") + 5, orderDatas[i].indexOf("&price"));
			body.append(names[i] + ",");
			prices[i] = Double
					.parseDouble(orderDatas[i].substring(orderDatas[i].indexOf("price=") + 6, orderDatas[i].length()));
			totalprice += prices[i];
			cartId[i] = Integer.parseInt(cartIds[i].substring(cartIds[i].indexOf("id=") + 3,cartIds[i].length()));
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderSn = simpleDateFormat.format(Calendar.getInstance().getTime());
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl("http://localhost:8080/ssm_business/paySuccess.form");
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		alipayRequest.setBizContent(
				"{\"out_trade_no\":\"" + orderSn + "\"," + "\"total_amount\":\"" + totalprice + "\"," + "\"subject\":\""
						+ "商品订单支付" + "\"," + "\"body\":\"" + body.substring(0, body.length() - 1).toString() + "\","
						+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		AlipayTradePagePayResponse alipayResponse = null;
		try {
			alipayResponse = alipayClient.pageExecute(alipayRequest);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("orderSn", orderSn);
		request.getSession().setAttribute("ids", ids);
		request.getSession().setAttribute("prices", prices);
		request.getSession().setAttribute("names", names);
		request.getSession().setAttribute("cartId", cartId);
		request.getSession().setAttribute("useraddress", useraddress);
		request.getSession().setAttribute("addressId", addressId);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(alipayResponse.getBody());
	}

	// 支付宝支付成功
	@RequestMapping("paySuccess.form")
	public String doOrderPaySuccess(HttpServletRequest request) {

		String orderSn = (String) request.getSession().getAttribute("orderSn");
		String[] ids = (String[]) request.getSession().getAttribute("ids");
		String[] names = (String[]) request.getSession().getAttribute("names");
		double[] prices = (double[]) request.getSession().getAttribute("prices");
		Integer[] cartId = (Integer[]) request.getSession().getAttribute("cartId");
		String useraddress = (String) request.getSession().getAttribute("useraddress");
		Integer addressId = (Integer) request.getSession().getAttribute("addressId");
		User user = (User) request.getSession().getAttribute("user");
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = dateFormat.format(date);
		
		for (int i = 0; i < ids.length; i++) {
			Order order = new Order();
			order.setOpus_id(Integer.parseInt(ids[i]));
			order.setOpus_name(names[i]);
			order.setOpus_price(prices[i]);
			order.setUser_id(user.getId());
			order.setUser_name(user.getName());
			order.setUser_address(useraddress);
			order.setAddress_id(addressId);
			order.setOrder_date(nowDate);
			order.setStatus("已支付");
			opusService.addOrder(order);
			opusService.deleteCartOpus(cartId[i]);
		}
		
		request.getSession().setAttribute("user", user);
		List<Order> orderList = opusService.seekOrder(user.getId());
		request.setAttribute("orderList", orderList);
		return "/order";
	}

	/* 查询某个人的订单信息 */

	@RequestMapping("seekOrder.form")
	public String seekOrder(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			List<Order> orderList = opusService.seekOrder(user.getId());
			request.setAttribute("orderList", orderList);
			return "order";
		}else{
			return "redirect:findAll.form";
		}
		
	}

	// 作品上传实现
	@RequestMapping("upload.form")
	public ModelAndView uploadOpus(MultipartFile fName, String title, String type, String tipic, double price,
			String create_time, String syponsis, String detail, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String orName = fName.getOriginalFilename();
		String realPath = request.getSession().getServletContext().getRealPath("/");
		realPath=realPath+"../image";
		System.out.println(realPath);

		File file2 = new File(realPath);

		if (!file2.exists()) {
			file2.mkdirs();
		}
		String uName = UUID.randomUUID().toString();
		String newFile = uName + orName.substring(orName.lastIndexOf("."));
		System.out.println(newFile);
		File file = new File(realPath + "/" + newFile);
		System.out.println();

		try {
			fName.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Opus opus = new Opus();
		opus.setAuthor_name(user.getName());
		opus.setOpus_create_time(create_time);
		opus.setOpus_heat(0);
		opus.setOpus_like_times(0);
		opus.setOpus_name(title);
		opus.setOpus_price(price);
		opus.setOpus_synopsis(syponsis);
		opus.setOpus_tipic(tipic);
		opus.setOpus_image("../image/" + newFile);
		opus.setUser_id(user.getId());
		if (user.getStatus() == 1) {
			opus.setStatus(0);
		} else {
			opus.setStatus(2);
		}
		opusService.upload(opus);
		RedirectView rv = new RedirectView("findAll.form");
		return new ModelAndView(rv);

	}

}
