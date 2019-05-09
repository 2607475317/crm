package com.w.crm.service;

import java.util.List;


import com.w.crm.pojo.BaseDict;

public interface BaseDictService {

	
	//查询
	public List<BaseDict> selectBaseDictListByCode(String code);
}
