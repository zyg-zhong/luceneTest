package com.cms.service;

import java.util.List;

import com.cms.entity.Carousel;

public interface CarouselService {
	//查看所有图片
	List<Carousel> queryAllCarouselList();
	//查一个
	Carousel queryOne(int id);
	//添加
	void addnewCarousel(Carousel carousel);
	//删除
	void removeOne(int id);
	//修改
	void updateOne(int carouselId, int status);
}
