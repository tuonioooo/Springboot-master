package com.master;

import com.master.bean.User;
import com.master.utils.DateUtils;
import com.master.utils.RedisTemplateUtil;
import com.master.utils.SHA256Utils;
import com.master.utils.UuidUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTokenApplicationTests {

	@Autowired
	private RedisTemplateUtil redisTemplateUtil;

	private static String  TOKEN_ACERT_KEY = "token_test";


	@Test
	public void contextLoads() {
	}


	@Test
	public void createToken(){
		User user = new User("10001","allen", "123456");
		long time = 1; //2分钟
		//密钥+uid+过期时间+SHA256算法
		StringBuilder sr = new StringBuilder();
		sr.append(TOKEN_ACERT_KEY).append(user.getUid()).append(time);
		String token = SHA256Utils.getSHA256StrJava(sr.toString(),null);
		System.out.println("创建Token：" + token);
		redisTemplateUtil.setEx("token", token, time, TimeUnit.MINUTES);
	}

	@Test
	public void validateToken(){
		String tokenParam = "f8d6603fe7770d94ff75b513f150b3dbda10870468589acd0566b028faa7f8c6";
		User user = new User("10001", "allen", "123456");
		long time = 1; //2分钟
		//密钥+uid+过期时间+SHA256算法
		StringBuilder sr = new StringBuilder();
		sr.append(TOKEN_ACERT_KEY).append(user.getUid()).append(time);
		String token = SHA256Utils.getSHA256StrJava(sr.toString(),null);
		if(tokenParam.equalsIgnoreCase(token)){
			Object obj = redisTemplateUtil.get("token");
			if(obj == null){
				System.out.println("校验失败");
			}else{
				System.out.println("校验成功");
			}
		}
	}

}
