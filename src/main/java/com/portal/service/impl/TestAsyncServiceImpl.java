package com.portal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 线程处理类.
 *
 * @author allen-zyg
 * @version 1.0.0
 */
@Service
public class TestAsyncServiceImpl {
    /**
     * 从工厂中获得日志对象.
     */
    private Logger logger = LoggerFactory.getLogger(TestAsyncServiceImpl.class);
    /**
     * 注入dao.
     */
 /*   @Autowired
    private ProductDao productDao;*/

    /**
     * 无返回值的任务.
     * @param string .
     */
    @Async //该注解表明此方法是异步方法
    public void excuteAsyncTask(String string) {
        //获得线程名称
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "开始执行了。");
        //处理一些没有返回值的业务。
        System.out.println("任务" + string);
        //写入日志
        logger.info("成功");
    }

    /**
     * 有返回值的任务
     * @return String .
     */
    public String resultThreadTask() {
        return "ok";
    }
}
