package com.cms.service.serviceImpl;

import java.util.List;

import com.cms.dao.CarouselDAO;
import com.cms.entity.Carousel;
import com.cms.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * carouselService.
 * @author allen-zyg
 * @version 1.0.0
 */
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService {
    /**
     * 创建dao层对象.
     */
    @Autowired
    private CarouselDAO cd;

    /**
     * 查所有.
     * @return List<Carousel>
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Carousel> queryAllCarouselList() {
        return cd.selectAllCarousel();
    }

    /**
     * 查一个.
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Carousel queryOne(int id) {

        return cd.selectOneCarousel(id);
    }

    /**
     * 添加
     * @param carousel .
     */
    @Override
    @Transactional
    public void addnewCarousel(Carousel carousel) {
        cd.insertNewCarousel(carousel);
    }

    /**
     * 删除.
     * @param id id.
     */
    @Override
    @Transactional
    public void removeOne(int id) {
        cd.deleteOneCarousel(id);
    }

    /**
     * 修改.
     * @param carouselId .
     * @param status .
     */
    @Override
    @Transactional
    public void updateOne(int carouselId, int status) {
        Carousel carousel = new Carousel();
        carousel.setCarouselId(carouselId);
        carousel.setStatus(status);
        //调方法
        cd.updateOneCarousel(carousel);
    }

}
