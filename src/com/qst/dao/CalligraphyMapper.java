package com.qst.dao;

import java.util.List;

import com.qst.entity.Calligraphy;

public interface CalligraphyMapper {

	List<Calligraphy> displaylist(int type_status);

	Calligraphy artContent(int id);
}
