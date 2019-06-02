package com.cms.service.serviceImpl;

import java.util.List;

import com.cms.dao.MasterDAO;
import com.cms.entity.Master;
import com.cms.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * masterService.
 * @author allen-zyg
 * @version 1.0.0
 */
@Service("masterService")
public class MasterServiceImpl implements MasterService {
    /**
     * 创建dao对象.
     */
    @Autowired
    private MasterDAO masterDAO;

    /**
     * 查所有上师.
     * @return List<Master>
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Master> queryAllMasters() {
        return masterDAO.selectAllMaster();
    }

}
