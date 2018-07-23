package com.master;

import com.master.async.Task;
import com.master.async.Task1;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAsyncApplicationTests {

	@Autowired
	private Task task;

	@Autowired
	private Task1 task1;

	@Test
	public void test() throws Exception {

		long start = System.currentTimeMillis();

		Future<String> task1 = task.doTaskOne();
		Future<String> task2 = task.doTaskTwo();
		Future<String> task3 = task.doTaskThree();

		while(true) {
			if(task1.isDone() && task2.isDone() && task3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}

		long end = System.currentTimeMillis();

		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");

	}

	@Test
	public void test1() throws Exception {
		long start = System.currentTimeMillis();

		task1.doTaskOne();
		task1.doTaskTwo();
		task1.doTaskThree();
		Thread.currentThread().join();
		long end = System.currentTimeMillis();

		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");

	}

	@Test
	@SneakyThrows
	public void test2() {
		for (int i = 0; i < 10000; i++) {
			task1.doTaskOne();
			task1.doTaskTwo();
			task1.doTaskThree();

			if (i == 9999) {
				System.exit(0);
			}
		}
	}
}
