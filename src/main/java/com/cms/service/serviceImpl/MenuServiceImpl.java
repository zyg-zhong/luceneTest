package com.cms.service.serviceImpl;


import com.cms.dao.MenuDAO;
import com.cms.entity.Menu;
import com.cms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * MenuServiceImpl.
 *
 * @author allen-zyg.
 * @version 1.0.0
 */
@Service
public class MenuServiceImpl implements MenuService {
    /**
     * MenuServiceImpl.
     */
    @Autowired
    private MenuDAO menuDAO;

    /**
     * get List<Menu>.
     *
     * @return List<Menu>.
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Menu> queryMenuList() {
        return menuDAO.selectAllMenus();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Menu queryOneMenuByParentId(int id) {
        return menuDAO.selectClildrenManu(id);
    }

}
