package com.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.cms.entity.Carousel;
import com.cms.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/carousel")
public class CarouselController {
	//创建service对象
	@Autowired
	private CarouselService carouselService;
	
	//查所有轮播图片
	@RequestMapping("/queryAllCarousel")
	@ResponseBody
	public List<Carousel> queryAllCarousel(){
		return carouselService.queryAllCarouselList();
	}
	//查一个-------用不到----直接在前台加载数据（getSelect方法）
	@RequestMapping("/queryOneCarousel")
	@ResponseBody
	public Carousel queryOneCarousel(int id){
		return carouselService.queryOne(id);
	}
	//添加
	@RequestMapping("/appendOneCarousel")
	@ResponseBody
	public void appendOneCarousel(MultipartFile caroufile,Carousel carousel) throws IllegalStateException, IOException{
		//uuid--->文件名
		String fileName=new Date()+""+caroufile.getOriginalFilename();
		//去空格
		String Num1=fileName.replace(":", "");
		String Num2=Num1.replace("+", "");
		String imgname=Num2.replace(" ", "");
		System.out.println("imgname:"+imgname);
		//上传到服务器
		File f=new File("E:\\apache-tomcat-7\\apache-tomcat-7.0.79\\webapps\\cmfz_zyg\\img\\carousel\\"+imgname);
		caroufile.transferTo(f);
		//设置名字
		carousel.setImgPath(imgname);
		//设置上传时间
		Date time=new Date();
		//设置id
		carousel.setCarouselId(0);
		carousel.setUploadDate(time);
		
		System.out.println("xxxxxx"+carousel);
		//调用业务
		carouselService.addnewCarousel(carousel);
	}
	//删除
	@RequestMapping("/removeOneCarousel")
	@ResponseBody
	public void removeOneCarousel(int carouselId){
		carouselService.removeOne(carouselId);
	}
	//修改
	@RequestMapping("/updateOneCarousel")
	@ResponseBody
	public void updateOneCarousel(int id,int sta){
		System.out.println(id+"---"+sta);
		carouselService.updateOne(id,sta);
	}
	
	
}
