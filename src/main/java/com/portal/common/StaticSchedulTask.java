package com.portal.common;

import com.portal.dao.IndexDao;
import com.portal.dao.ProductDao;
import com.portal.pojo.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author allen-zyg
 * @version 1.0.0
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class StaticSchedulTask {

    /**注入dao.*/
    @Autowired
    private ProductDao productDao;
    /**注入索引dao.*/
    @Autowired
    private IndexDao indexDao;

    /**
     * 3.添加定时任务.
     */
    @Scheduled(cron = "0 */1 * * * ?") //一分钟执行一次
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        long startTime = System.currentTimeMillis();    //获取开始时间
        //查询数据库获得全部商品
        List<Products> proList = productDao.selectAll();
        //创建商品的全文索引
        indexDao.createDump(proList);
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间

    }
}
