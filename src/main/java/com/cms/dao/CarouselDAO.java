package com.cms.dao;


import java.util.List;

import com.cms.entity.Carousel;

public interface CarouselDAO {
	//查看所有轮播图片
	List<Carousel> selectAllCarousel();
	//查一个
	Carousel selectOneCarousel(int id);
	//添加
	void insertNewCarousel(Carousel carousel);
	//删除
	void deleteOneCarousel(int id);
	//修改
	void updateOneCarousel(Carousel carousel);
}
