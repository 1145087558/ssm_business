package com.qst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qst.dao.CalligraphyMapper;
import com.qst.entity.Calligraphy;
import com.qst.service.CalligraphyService;
@Service
public class CalligraphyServiceImpl implements CalligraphyService{

	@Autowired
	CalligraphyMapper calMapper;
	@Override
	public List<Calligraphy> displaylist(int type_status) {
		List<Calligraphy> calList=calMapper.displaylist(type_status);
		return calList;
	}
	@Override
	public Calligraphy artContent(int id) {
		Calligraphy cal=new Calligraphy();
		cal=calMapper.artContent(id);
		return cal;
	}

}
