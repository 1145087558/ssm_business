package com.qst.backstagecontroller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import xyz.peter.UpLoadImageBed;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.qst.entity.Cart;
import com.qst.entity.Discuss;
import com.qst.entity.Opus;
import com.qst.entity.OpusType;
import com.qst.entity.Order;
import com.qst.entity.Tipic;
import com.qst.entity.User;
import com.qst.service.OpusService;
import com.qst.service.UserService;
import com.qst.util.AlipayConfig;

@Controller
@RequestMapping("/backstage")
public class OpusBackstageController {

	@Autowired
	OpusService opusService;
	@Autowired
	UserService userService;

	/**
	 * 作品管理，统计点赞数排名前十的作品
	 */
	@RequestMapping("liketoplist.form")
	@ResponseBody
	public List<Opus> liketoplist(HttpServletRequest request, HttpServletResponse resp) {

		return opusService.liketoplist();
	}

	/**
	 * 作品管理，如果是普通会员就获取自己所上传的作品，而管理员是获取所有已经上传的作品
	 */
	@RequestMapping("getOpusAll.form")
	public String getOpusAll(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<Opus> opusList = null;
		if (user != null) {
			if (user.getStatus() == 0) {
				opusList = opusService.seekOpusByUserId(user.getId());
				request.setAttribute("opusList", opusList);
			} else {
				opusList = opusService.getOpusAll();
				request.setAttribute("opusList", opusList);
			}
		}
		return "backstage/product-brand";
	}

	/**
	 * 作品管理，对上传的作品的进行上架或者下架(状态进行修改)
	 */
	@RequestMapping("modifyOpusByStatus.form")
	public void modifyOpusByStatus(int status, int id) {
		opusService.modifyOpusByStatus(status, id);
	}

	/**
	 * 作品管理，通过id来获取单个作品的信息
	 */
	@RequestMapping("getOpusById.form")
	public String getOpusById(int id, Model model) {
		Opus opus = opusService.opusDetail(id);
		model.addAttribute("opus", opus);
		return "backstage/picture-modify";
	}

	/**
	 * 作品管理，把题材信息传到添加作品页面
	 */
	@RequestMapping("toAddOpus.form")
	public String toAddOpus(Model model) {
		List<Tipic> tipicList = opusService.getTipicAll();
		model.addAttribute("tipicList", tipicList);
		List<OpusType> typeList = opusService.getTypeAll();
		model.addAttribute("typeList", typeList);
		return "backstage/picture-add";
	}

	/**
	 * 作品管理，根据传的关键字(作品类型、作家名、作品名)进行搜索
	 */
	@RequestMapping("search.form")
	public String searchOpus(String search, HttpServletRequest request) {
		System.out.println("接收到的关键字：" + search);
		List<Opus> opusList = opusService.opusSearch(search);
		request.setAttribute("opusList", opusList);
		return "homepage";
	}

	/**
	 * 作品管理，通过id对已经上传过的作品的进行修改
	 */
	@RequestMapping("modifyOpus.form")
	public void uploadOpus(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {

		Opus opus = new Opus();
		String id = request.getParameter("id");
		String opus_name = request.getParameter("opus_name");
		String author_name = request.getParameter("author_name");
		String opus_tipic = request.getParameter("opus_tipic");
		String opus_price = request.getParameter("opus_price");
		String opus_synopsis = request.getParameter("opus_synopsis");

		opus.setId(Integer.parseInt(id));
		opus.setOpus_name(opus_name);
		opus.setAuthor_name(author_name);
		opus.setOpus_price(Double.parseDouble(opus_price));
		opus.setOpus_synopsis(opus_synopsis);
		opus.setOpus_tipic(opus_tipic);

		if (files != null) {
			String path = request.getServletContext().getRealPath("/");
			String uName = UUID.randomUUID().toString();
			path = path + "../image/";

			File file = new File(path);

			if (!file.exists()) {
				file.mkdirs();
			}
			MultipartFile mutipart;
			String newFile = "";
			for (int i = 0; i < files.length; i++) {
				mutipart = files[i];
				String fileName = mutipart.getOriginalFilename();
				newFile = uName + fileName.substring(fileName.lastIndexOf("."));
				File file2 = new File(path, newFile);
				try {
					mutipart.transferTo(file2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			opus.setOpus_image("../image/" + newFile);
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user.getStatus() == 1) {
			opus.setStatus(0);
		} else {
			opus.setStatus(2);
		}
		opusService.updateOpus(opus);
	}

	/**
	 * 作品管理，上传作品，如果是普通用户上传的作品需要进行审核，如果是管理员则是直接上架
	 */
	@RequestMapping("addOpus.form")
	public void addOpus(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		Opus opus = new Opus();
		String opus_name = request.getParameter("opus_name");
		String author_name = request.getParameter("author_name");
		String opus_tipic = request.getParameter("opus_tipic");
		String opus_price = request.getParameter("opus_price");
		String opus_synopsis = request.getParameter("opus_synopsis");

		opus.setOpus_name(opus_name);
		opus.setAuthor_name(author_name);
		opus.setOpus_price(Double.parseDouble(opus_price));
		opus.setOpus_synopsis(opus_synopsis);
		opus.setOpus_tipic(opus_tipic);
		System.out.println(opus.getOpus_synopsis());
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		opus.setOpus_create_time(dateFormat.format(date));
		if (files != null) {
			String path = request.getServletContext().getRealPath("/");
			String uName = UUID.randomUUID().toString();
			path = path + "../image/";

			File file = new File(path);

			if (!file.exists()) {
				file.mkdirs();
			}
			MultipartFile mutipart;
			String newFile = "";
			for (int i = 0; i < files.length; i++) {
				mutipart = files[i];
				String fileName = mutipart.getOriginalFilename();
				newFile = uName + fileName.substring(fileName.lastIndexOf("."));
				File file2 = new File(path, newFile);
				try {
					mutipart.transferTo(file2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			opus.setOpus_image("../image/" + newFile);
		}

		if (user.getStatus() == 1) {
			opus.setStatus(0);
		} else {
			opus.setStatus(2);
		}
		opus.setUser_id(user.getId());
		opusService.upload(opus);
	}

	/**
	 * 作品管理，上传作品（没有上传图片）
	 */
	@RequestMapping("addOpusNoFile.form")
	public String addOpusNoFile(Opus opus, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		if (user.getStatus() == 1) {
			opus.setStatus(0);
		} else {
			opus.setStatus(2);
		}

		opus.setUser_id(user.getId());
		opusService.upload(opus);
		return "redirect:getOpusAll.form";
	}

	/**
	 * 作品管理，只修改了作品的信息，而没有重新上传图片
	 */
	@RequestMapping("modifyOpusNoFile.form")
	public String modifyOpusNoFile(Opus opus, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user.getStatus() == 1) {
			opus.setStatus(0);
		} else {
			opus.setStatus(2);
		}
		opusService.updateOpus(opus);
		return "redirect:getOpusAll.form";
	}

	/**
	 * 作品管理，获取所有未审核的作品
	 */
	@RequestMapping("getOpusWsh.form")
	public String getOpusWsh(HttpServletRequest request) {

		List<Opus> opusList = opusService.getOpusByStatus(2);
		request.setAttribute("opusList", opusList);
		return "backstage/picture-list";
	}

	/**
	 * 作品管理，获取所有退款未审核的订单
	 */
	@RequestMapping("getOrderWsh.form")
	public String getOrderWsh(HttpServletRequest request) {

		List<Order> orderList = opusService.getOrderByStatus("退款中");
		request.setAttribute("orderList", orderList);
		return "backstage/ordersh-list";
	}

	/**
	 * 订单页面，对退款的申请不通过
	 */
	@RequestMapping("refundBTG.form")
	public void refundBTG(String out_trade_no, HttpServletRequest request) {

		Order order = opusService.seekOrderByNumber(out_trade_no);
		order.setStatus("已支付");
		opusService.updateOrder(order);
	}

	/**
	 * 订单页面，根据所支付的类型进行退款
	 */
	@RequestMapping("refundRequest.form")
	public void refundRequest(String out_trade_no, HttpServletRequest request) {
		System.out.println(out_trade_no);
		Order order = opusService.seekOrderByNumber(out_trade_no);
		if (order.getOrder_type().equals("余额")) {
			User user = (User) request.getSession().getAttribute("user");
			user.setBalance(user.getBalance() + order.getOpus_price());
			userService.modifyBalance(user);
			order.setStatus("已退款");
			opusService.updateOrder(order);
			request.getSession().setAttribute("user", user);

		} else if (order.getOrder_type().equals("支付宝")) {
			try {
				AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
						AlipayConfig.merchant_private_key, "json", AlipayConfig.charset,
						AlipayConfig.zifubao_public_key, AlipayConfig.sign_type);
				AlipayTradeRefundRequest aliRequest = new AlipayTradeRefundRequest();
				aliRequest.setBizContent("{" + "\"out_trade_no\":\"" + out_trade_no + "\"," + "\"refund_amount\":\""
						+ order.getOpus_price() + "\"," + "\"refund_reason\":\"正常退款\"" + " }");
				AlipayTradeRefundResponse response;
				response = alipayClient.execute(aliRequest);
				if (response.isSuccess()) {
					System.out.println("支付宝退款成功");
					order.setStatus("已退款");
					opusService.updateOrder(order);
				} else {
					// response.getSubMsg();//失败会返回错误信息(出现交易信息被篡改一般是同一个订单被多次退款)
					System.out.println(response.getSubMsg());
				}
			} catch (AlipayApiException e) {
				order.setStatus("已退款");
				opusService.updateOrder(order);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 作品管理，通过id删除已经上传的作品
	 */
	@RequestMapping("deleteOpus.form")
	public void deleteOpus(int id, HttpServletRequest request) {

		opusService.deleteOpus(id);
	}

	/**
	 * 作品管理，获取所有题材
	 */
	@RequestMapping("getTipic.form")
	public String getTipic(Model model) {

		List<Tipic> tipicList = opusService.getTipicAll();

		model.addAttribute("tipicList", tipicList);

		return "backstage/tipic-list";
	}

	/**
	 * 作品管理，添加题材
	 */
	@RequestMapping("addTipic.form")
	public void addTipic(Tipic tipic) {

		opusService.addTipic(tipic);
	}

	/**
	 * 作品管理，删除题材
	 */
	@RequestMapping("deleteTipic.form")
	public void deleteTipic(int id) {

		opusService.deleteTipic(id);
	}

	/**
	 * 作品管理，获取所有类型
	 */
	@RequestMapping("getType.form")
	public String getType(Model model) {

		List<OpusType> typeList = opusService.getTypeAll();

		model.addAttribute("typeList", typeList);

		return "backstage/type-list";
	}

	/**
	 * 作品管理，添加类型
	 */
	@RequestMapping("addType.form")
	public void addType(OpusType opusType) {

		opusService.addType(opusType);
	}

	/**
	 * 作品管理，删除类型
	 */
	@RequestMapping("deleteType.form")
	public void deleteType(int id) {

		opusService.deleteType(id);
	}

	/**
	 * 作品管理，删选作品
	 */
	@RequestMapping("searchOpus.form")
	public String searchOpus(HttpServletRequest request,Opus opus) {
		
		if("".equals(opus.getOpus_name())){
			opus.setOpus_name(null);
		}
		if("全部".equals(opus.getOpus_tipic())){
			opus.setOpus_tipic(null);
		}
		System.out.println(opus.getOpus_name());
		List<Opus> opusList = opusService.searchOpus(opus);
		request.setAttribute("opusList", opusList);
		return "backstage/product-brand";

	}
}
