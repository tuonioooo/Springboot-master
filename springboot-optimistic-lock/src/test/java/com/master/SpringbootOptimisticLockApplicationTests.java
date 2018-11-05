package com.master;

import com.master.bean.Tgoodsinfo;
import com.master.service.TgoodsinfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootOptimisticLockApplicationTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TgoodsinfoService tgoodsinfoService;

	private Integer requestNum = 8;

	private CountDownLatch countDownLatch = new CountDownLatch(requestNum);

	/**
	 * 初始化数据
	 */
	@Before
	public void init(){

		List<Tgoodsinfo> tgoodsinfos =  tgoodsinfoService.findAll();

		tgoodsinfos.stream().forEach(tgoodsinfo -> tgoodsinfoService.delete(tgoodsinfo.getId()));

		Tgoodsinfo tgoodsinfo1 = new Tgoodsinfo(1,"10001",10,0,null);
		Tgoodsinfo tgoodsinfo2 = new Tgoodsinfo(2,"10002",20,0,null);
		Tgoodsinfo tgoodsinfo3 = new Tgoodsinfo(3,"10003",30,0,null);

		tgoodsinfoService.save(tgoodsinfo1);
		tgoodsinfoService.save(tgoodsinfo2);
		tgoodsinfoService.save(tgoodsinfo3);

	}

	@Test
	public void contextLoads() {


	}

	/**
	 * 不加锁的错误演示，会发生库存超卖的情况
	 */
	@Test
	public void test1(){
		for(int i=0; i < requestNum; i++){
			new Thread(()->{
				try {
					countDownLatch.await();
					boolean result = tgoodsinfoService.updateGoodsAmout("10001", 1);
					if(result){
						Tgoodsinfo tgoodsinfo = tgoodsinfoService.get("10001");
						logger.info("库存扣减成功"+ "，剩余库存" + tgoodsinfo.getAmout());
					}else{
						logger.info("库存扣减失败");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
			countDownLatch.countDown();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 数据库乐观锁基于版本号实现并发控制——正确演示
	 */
	@Test
	public void test2(){
		for(int i=0; i < requestNum; i++){
			new Thread(()->{
				try {
					countDownLatch.await();
					boolean result = tgoodsinfoService.updateGoodsAmout1("10003", 20);
					if(result){
						Tgoodsinfo tgoodsinfo = tgoodsinfoService.get("10003");
						logger.info("库存扣减成功"+ "，剩余库存" + tgoodsinfo.getAmout());
					}else{//为何扣减失败，是数据库在扣减时，版本号已经更新了，之前的版本号不存在，所以会扣减失败
						Tgoodsinfo tgoodsinfo = tgoodsinfoService.get("10001");
						logger.info("库存扣减失败"+ "，剩余库存" + tgoodsinfo.getAmout());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
			countDownLatch.countDown();
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数据库乐观锁基于状态实现并发控制——正确演示
	 */
	@Test
	public void test3(){
		for(int i=0; i < requestNum; i++){
			new Thread(()->{
				try {
					countDownLatch.await();
					boolean result = tgoodsinfoService.updateGoodsAmout1("10003", 20);
					if(result){
						Tgoodsinfo tgoodsinfo = tgoodsinfoService.get("10003");
						logger.info("库存扣减成功"+ "，剩余库存" + tgoodsinfo.getAmout());
					}else{//为何扣减失败，是数据库在扣减时，版本号已经更新了，之前的版本号不存在，所以会扣减失败
						Tgoodsinfo tgoodsinfo = tgoodsinfoService.get("10001");
						logger.info("库存扣减失败"+ "，剩余库存" + tgoodsinfo.getAmout());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
			countDownLatch.countDown();
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}





}
