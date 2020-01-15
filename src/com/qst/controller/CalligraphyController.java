package com.qst.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qst.entity.Calligraphy;
import com.qst.service.CalligraphyService;

@Controller
public class CalligraphyController {

	@Autowired
	CalligraphyService calService;
	//所有的书法知识，在前端只展示标题和作者
	@RequestMapping("calligraphy.form")
	public String calligraphy(HttpServletRequest request,int type_status){
		List<Calligraphy> calList=calService.displaylist(type_status);
		request.setAttribute("calList", calList);
		//System.out.println("第一条信息："+calList.get(0).getTitle());
		//System.out.println("总记录："+calList.size());
		return "sfzs";
	}
	//某一条书法知识的内容
	@RequestMapping("art_content.form")
	public String artContent(HttpServletRequest request,int id){
		Calligraphy cal=new Calligraphy();
		cal=calService.artContent(id);
		List<Calligraphy> calList=calService.displaylist(0);
		request.setAttribute("calList", calList);
		request.setAttribute("cal", cal);
		return "sfzs_content";
		
	}
}
