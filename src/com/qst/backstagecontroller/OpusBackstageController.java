package com.qst.backstagecontroller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUpload;
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
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.qst.entity.Cart;
import com.qst.entity.Discuss;
import com.qst.entity.Opus;
import com.qst.entity.Order;
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

	// 展示首页作品
	@RequestMapping("liketoplist.form")
	@ResponseBody
	public List<Opus> liketoplist(HttpServletRequest request, HttpServletResponse resp) {

		return opusService.liketoplist();

	}

	// 管理上传作品
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

	// 作品上架和下架
	@RequestMapping("modifyOpusByStatus.form")
	public void modifyOpusByStatus(int status, int id) {
		opusService.modifyOpusByStatus(status, id);
	}

	// 获取作品信息
	@RequestMapping("getOpusById.form")
	public String getOpusById(int id, Model model) {
		Opus opus = opusService.opusDetail(id);
		model.addAttribute("opus", opus);
		return "backstage/picture-modify";
	}

	// 作品搜索（搜索作家名字）
	@RequestMapping("search.form")
	public String searchOpus(String search, HttpServletRequest request) {
		System.out.println("接收到的关键字：" + search);
		List<Opus> opusList = opusService.opusSearch(search);
		request.setAttribute("opusList", opusList);
		return "homepage";
	}

	// 作品上传实现
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

	// 作品上传实现
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

	// 作品上传无图片
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

	// 作品修改无图片
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

	// 获取未审核作品
	@RequestMapping("getOpusWsh.form")
	public String getOpusWsh(HttpServletRequest request) {

		List<Opus> opusList = opusService.getOpusByStatus(2);
		request.setAttribute("opusList", opusList);
		return "backstage/picture-list";
	}

	// 作品删除
	@RequestMapping("deleteOpus.form")
	public void deleteOpus(int id, HttpServletRequest request) {

		opusService.deleteOpus(id);
	}

}
