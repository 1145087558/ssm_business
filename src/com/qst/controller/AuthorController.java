package com.qst.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qst.entity.Author;
import com.qst.entity.Opus;
import com.qst.service.AuthorService;
import com.qst.service.OpusService;
@Controller
public class AuthorController {

	@Autowired
	AuthorService authorService;
	@Autowired
	OpusService opusService;
	
	//取出所有的作家
		@RequestMapping("allAuthor.form")
		public String allAuthor(HttpServletRequest request){
			List<Author> authorList=authorService.allAuthor();
			List<Opus> opusList=opusService.findAll();
			request.setAttribute("authorList",authorList);	
			request.setAttribute("opusList",opusList);
			System.out.println("作品位置："+opusList.get(0).getOpus_image());
			System.out.println("第一位姓名："+authorList.get(0).getName());
			return "author";
						
		}
}
