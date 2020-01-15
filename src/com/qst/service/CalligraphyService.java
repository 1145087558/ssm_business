package com.qst.service;

import java.util.List;

import com.qst.entity.Calligraphy;


public interface CalligraphyService {

	List<Calligraphy> displaylist(int type_status);

	Calligraphy artContent(int id);

}
