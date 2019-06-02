package com.example.demo;

import com.portal.service.impl.TestAsyncServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class DemoApplicationTests {
	/**
	 * 注入TestAsyncService .
	 */
	@Autowired
	private TestAsyncServiceImpl testAsyncService;
	/**
	 * 测试无返回值异步任务.
	 */
	@Test
	public void test() {
		//调用业务去打印
		testAsyncService.excuteAsyncTask("无返回值异步任务执行了。");
	}

}
