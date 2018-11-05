package com.master;

import com.master.bean.TGoodsInfo;
import com.master.dao.TGoodsInfoRepository;
import com.master.service.TGoodsInfoService;
import com.master.utils.MemcachedUtils;
import com.master.utils.RedisTemplateUtil;
import net.spy.memcached.MemcachedClient;
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
public class SpringbootSeckillRedisApplicationTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TGoodsInfoService tGoodsInfoService;

	private Integer requestNum = 8;

	private CountDownLatch countDownLatch = new CountDownLatch(requestNum);

	@Autowired
	private TGoodsInfoRepository tGoodsInfoRepository;

	@Autowired
	private RedisTemplateUtil redisTemplateUtil;

	@Test
	public void contextLoads() {
	}

	/**
	 * 初始化数据
	 */
	@Before
	public void init(){

		List<TGoodsInfo> tgoodsinfos =  tGoodsInfoRepository.findAll();

		tGoodsInfoRepository.deleteAll();

		TGoodsInfo tgoodsinfo1 = new TGoodsInfo(1,"10001",10,0);
		TGoodsInfo tgoodsinfo2 = new TGoodsInfo(2,"10002",20,0);
		TGoodsInfo tgoodsinfo3 = new TGoodsInfo(3,"10003",30,0);

		tGoodsInfoRepository.save(tgoodsinfo1);
		tGoodsInfoRepository.save(tgoodsinfo2);
		tGoodsInfoRepository.save(tgoodsinfo3);


		//初始化缓存中的数据
		if(redisTemplateUtil.hasKey(tgoodsinfo1.getCode())){
			redisTemplateUtil.delete(tgoodsinfo1.getCode());
		}
		if(redisTemplateUtil.hasKey(tgoodsinfo2.getCode())){
			redisTemplateUtil.delete(tgoodsinfo2.getCode());
		}
		if(redisTemplateUtil.hasKey(tgoodsinfo3.getCode())){
			redisTemplateUtil.delete(tgoodsinfo3.getCode());
		}


		redisTemplateUtil.set(tgoodsinfo1.getCode(), tgoodsinfo1.getAmout());
		redisTemplateUtil.set(tgoodsinfo2.getCode(), tgoodsinfo2.getAmout());
		redisTemplateUtil.set(tgoodsinfo3.getCode(), tgoodsinfo3.getAmout());


		//初始化Memcached数据
		MemcachedClient client = MemcachedUtils.getClient();
		if(client.get(tgoodsinfo3.getCode()) != null ){
			client.delete(tgoodsinfo3.getCode());
		}
		client.set(tgoodsinfo3.getCode(), 10000, tgoodsinfo3.getAmout());


	}

	/**
	 * 基于Redis缓存实现秒杀架构示例
	 */
	@Test
	public void test1(){
		String code = "10003";
		for(int i=0; i < requestNum; i++){//共有8个请求同时购买
			new Thread(()->{
				try {
					countDownLatch.await();
					tGoodsInfoService.updateGoodsAmout(code, 2);//每个请求购买2个产品
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

		//获取缓存的库存同步到数据库
		TGoodsInfo tGoodsInfo = this.tGoodsInfoRepository.findByCode(code);
		Integer stock = (Integer) this.redisTemplateUtil.get(code);
		tGoodsInfo.setAmout(stock);
		this.tGoodsInfoRepository.save(tGoodsInfo);
		System.out.println("剩余库存是多少 = " + tGoodsInfo.getAmout());

	}

	/**
	 * 基于Memcached缓存实现秒杀架构示例
	 */
	@Test
	public void test2(){
		String code = "10003";
		for(int i=0; i < requestNum; i++){//共有8个请求同时购买
			new Thread(()->{
				try {
					countDownLatch.await();
					boolean result = tGoodsInfoService.updateGoodsAmout2(code, 2);//每个请求购买2个产品
					MemcachedClient client = MemcachedUtils.getClient();
					if(result){
						Integer amount = (Integer) client.get(code);
						logger.info("库存扣减成功"+ "，剩余库存" + amount);
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
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

}
