package com.cms.dao;

import java.util.List;

import com.cms.entity.Master;

public interface MasterDAO {
	//查所有上师
	List<Master> selectAllMaster();
}
