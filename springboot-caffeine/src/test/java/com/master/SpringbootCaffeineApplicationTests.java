package com.master;

import com.master.bean.TGoodsInfo;
import com.master.service.TGoodsInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCaffeineApplicationTests{

	@Autowired
	private TGoodsInfoService tGoodsInfoService;

	@Test
	public void contextLoads() {
		tGoodsInfoService.findGoods(1);
	}

}
