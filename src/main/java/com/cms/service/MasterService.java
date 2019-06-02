package com.cms.service;

import java.util.List;

import com.cms.entity.Master;

/**
 * MasterService.
 * @version 1.0.0
 * @author allen-zyg
 */
public interface MasterService {
	/**
	 * 查所有上师
	 * @return List<Master> .
	 */
	List<Master> queryAllMasters();
}
