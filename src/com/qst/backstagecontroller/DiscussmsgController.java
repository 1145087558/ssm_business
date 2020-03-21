package com.qst.backstagecontroller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qst.entity.Discuss;
import com.qst.service.OpusService;

@Controller
@RequestMapping("/backstage")
public class DiscussmsgController {

	@Autowired
	OpusService opusService;

	/**
	 * 评论管理，管理员获取所有的评论
	 */
	@RequestMapping("getDiscussmsgAll.form")
	public String getOpusAll(HttpServletRequest request) {
		List<Discuss> discussList = opusService.seekDiscussMsgAll();
		request.setAttribute("discussList", discussList);
		return "backstage/feedback-list";
	}

	/**
	 * 评论管理，通过id获取单条评论
	 */
	@RequestMapping("getDiscussmsg.form")
	public String getDiscussMsg(int id, Model model) {

		model.addAttribute("discuss", opusService.getDiscussMsg(id));
		return "backstage/feedback-modify";
	}

	/**
	 * 评论管理，对单条评论进行修改
	 */
	@RequestMapping("updateDiscussmsg.form")
	public void updateDiscussMsg(Discuss discuss) {
		opusService.updateDiscussMsg(discuss);
	}

	/**
	 * 评论管理，对某条评论进行删除
	 */
	@RequestMapping("deleteDiscussmsg.form")
	public void deleteDiscussmsg(int id, HttpServletRequest request) {
		opusService.deleteDiscussmsg(id);

	}
}
