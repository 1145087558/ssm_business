package com.qst.backstagecontroller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qst.entity.Discuss;
import com.qst.service.OpusService;

@Controller
@RequestMapping("/backstage")
public class DiscussmsgController {

	@Autowired
	OpusService opusService;

	// 评论管理
	@RequestMapping("getDiscussmsgAll.form")
	public String getOpusAll(HttpServletRequest request) {
		List<Discuss> discussList = opusService.seekDiscussMsgAll();
		request.setAttribute("discussList", discussList);
		return "backstage/feedback-list";
	}

	// 评论管理
	@RequestMapping("deleteDiscussmsg.form")
	public void deleteDiscussmsg(int id,HttpServletRequest request) {
		opusService.deleteDiscussmsg(id);
		
	}
}
