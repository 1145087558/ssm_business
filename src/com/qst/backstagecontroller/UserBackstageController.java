package com.qst.backstagecontroller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.qst.util.VerificationCode;

@Controller
@RequestMapping("/backstage")
public class UserBackstageController {

	@Autowired
	UserService userService;

	// 后台登录
	@RequestMapping("/backstageLogin.form")
	public String backstageLogin(User user, HttpServletRequest request) {
		System.out.println("手机号：" + user.getTel() + "密码：" + user.getPwd());
		user = userService.userLogin(user);
		if (user == null) {
			request.setAttribute("error", "账号或者密码错误!");
			return "backstage/login";
		}else{
			if(user.getStatus()==2||user.getStatus()==3){
				request.setAttribute("error", "用户被禁用或者已删除!");
				return "backstage/login";
			}
		}
		System.out.println("用户ID：" + user.getId());
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		return "backstage/index";
	}

	// 退出
	@RequestMapping("/logout.form")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();

		return "backstage/login";
	}

	// 绘画验证码
	@RequestMapping("/drawImage.form")
	public void drawImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		VerificationCode verificationCode = new VerificationCode();
		BufferedImage image = verificationCode.getImage();
		String text = verificationCode.getText();
		StringBuffer randomCode = new StringBuffer();
		randomCode.append(text);
		request.getSession().setAttribute("serverCheckcode", randomCode.toString());
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(image, "jpeg", sos);
		sos.flush();
		sos.close();
	}

	// 判断验证码
	@RequestMapping("/checkCode.form")
	public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String serverCheckcode = (String) request.getSession().getAttribute("serverCheckcode");
		boolean result;
		String clientCheckcode = (String) request.getParameter("signcode");
		System.out.println(clientCheckcode);
		if (clientCheckcode == null) {
			result = false;
			String confirmJson = JSON.toJSONString(result);
			response.getWriter().print(confirmJson);
			return;
		}
		if (clientCheckcode.toLowerCase().equals(serverCheckcode.toLowerCase())) {
			result = true;
			String confirmJson = JSON.toJSONString(result);
			response.getWriter().print(confirmJson);
		} else {
			result = false;
			String confirmJson = JSON.toJSONString(result);
			response.getWriter().print(confirmJson);
		}
	}

	// 获取普通用户列表
	@RequestMapping("/getUserList.form")
	public String getUserList(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<User> users;
		if (user.getStatus() == 0) {
			users = new ArrayList<User>();
			users.add(user);
		} else {
			users = userService.getOrdinaryUserAll();
		}
		request.setAttribute("users", users);

		return "backstage/member-list";
		
	}
	
	// 获取删除用户列表
	@RequestMapping("/getDeleteUserList.form")
	public String getDeleteUserList(Model model) {

		List<User> users = userService.getUserByStatus(3);
		model.addAttribute("users", users);

		return "backstage/member-del";
	}
	/*//等级管理
	@RequestMapping*/
	// 获取所有用户列表
	@RequestMapping("/getUserAllList.form")
	public String getUserAll(Model model) {

		List<User> users = userService.getUserAll();
		model.addAttribute("users", users);

		return "backstage/admin-role";
	}

	// 获取管理员列表
	@RequestMapping("/getAdminUser.form")
	public String getAdminUser(Model model) {

		List<User> users = userService.getUserByStatus(1);
		model.addAttribute("users", users);

		return "backstage/admin-list";
	}

	// 展示用户信息
	@RequestMapping("/showUser.form")
	public String showUser(String type, int userId, Model model) {

		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		if ("show".equals(type)) {
			return "backstage/member-show";
		} else if ("modify".equals(type)) {
			return "backstage/member-modify";
		} else if ("modifyPassword".equals(type)) {
			return "backstage/change-password";
		}
		return "";
	}

	// 修改用户信息
	@RequestMapping("/modifyUser.form")
	public String modifyUser(User user) {
		userService.modifyPersonMsg(user);

		return "redirect:getUserList.form";
	}

	// 添加用户
	@RequestMapping("/addUser.form")
	public String addUser(User user) {

		userService.addUser(user);

		return "redirect:getUserList.form";
	}

	// 修改密码
	@RequestMapping("/modifyPassword.form")
	public String modifyPassword(int id, String newpassword) {

		userService.modifyPassword(id, newpassword);

		return "redirect:getUserList.form";
	}

	// 用户删除
	@RequestMapping("/deleteUser.form")
	public String deleteUser(int id) {

		userService.deleteUser(id);

		return "redirect:getUserList.form";
	}

	// 多个用户删除
	@RequestMapping("/deletesUser.form")
	public void deletesUser(Integer[] id) {

		if (id != null) {
			for (int i : id)
				userService.deleteUser(i);
		}
	}

	// 用户启用停用
	@RequestMapping("/modifyUserStatus.form")
	public void modifyUserStatus(int id, int status) {

		userService.modifyUserStatus(id, status);
	}

	// 多个用户状态删除
	@RequestMapping("/modifyUsersStatus.form")
	public void modifyUsersStatus(Integer[] id, int status, HttpServletResponse response) {

		if (id != null) {
			for (int i : id)
				userService.modifyUserStatus(i, status);
		}
	}
	//地图统计用户地址数量
	@RequestMapping("/mapUser.form")
	@ResponseBody
	public List<UserAddress> mapUser( HttpServletResponse resp,HttpServletRequest req) {
		return  userService.mapUser();
		
		
	}
}
