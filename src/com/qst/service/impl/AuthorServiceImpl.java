package com.qst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qst.dao.AuthorMapper;
import com.qst.entity.Author;
import com.qst.service.AuthorService;
@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	AuthorMapper authorMapper;
	
	@Override
	public List<Author> allAuthor() {
		List<Author> authorList=authorMapper.allAuthor();
		return authorList;
	}

}
