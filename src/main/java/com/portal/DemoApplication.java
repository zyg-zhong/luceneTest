package com.portal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
 *入口类.
 */
@EnableAsync//开启异步
@SpringBootApplication
@MapperScan(basePackages = "com.cms.dao,com.port.dao")//扫描dao-mapper
public class DemoApplication {
    /**
     * 主函数.
     * @param args .
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
