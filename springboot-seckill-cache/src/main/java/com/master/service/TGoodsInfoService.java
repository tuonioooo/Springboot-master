package com.master.service;

import com.master.bean.TGoodsInfo;
import com.master.dao.TGoodsInfoRepository;
import com.master.utils.JedisUtil;
import com.master.utils.MemcachedUtils;
import com.master.utils.RedisTemplateUtil;
import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author tuonioooo
 * @Date  2018-11-05
 * @info  业务接口
 */
@Service
public class TGoodsInfoService {

    @Autowired
    private TGoodsInfoRepository tGoodsInfoRepository;

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    private static MemcachedClient client = MemcachedUtils.getClient();

    public List<TGoodsInfo> findAll(){

        return tGoodsInfoRepository.findAll();
    }


    /**
     *基于Redis缓存实现——秒杀服务
     */
    public boolean updateGoodsAmout(String code, int buys){
        //用Redis的原子操作减库存，防止并发操作
        Long stock = redisTemplateUtil.incrBy(code, -buys);
        if(stock == null || stock < 0){
            //还原库存里的库存
            redisTemplateUtil.incrBy(code, +buys);
            return false;
        }
        return true;
    }


    /**
     *基于Memcache缓存实现——秒杀服务
     */
    public boolean updateGoodsAmout2(String code, int buys){
        //1.获取商品库存对象
        CASValue<Object> tgoodsinfo = client.gets(code);
        //2.获取版本号
        long version = tgoodsinfo.getCas();
        //3.获取库存数量
        Integer amout = Integer.parseInt(tgoodsinfo.getValue().toString().trim());

        //如果库存不够直接返回
        if(amout < buys){
            return false;
        }

        //使用cas更新数据时带上版本号
        CASResponse casResponse = client.cas(code, version, amout-buys);
        System.out.println("casResponse = " + casResponse);  //OK 、EXISTS
        if(casResponse.toString().equals("OK")){
            return true;
        }

        //如果更新失败，当前线程休眠，措峰执行
        waitForLock();
        //递归调用本身
        return updateGoodsAmout2(code, buys);
    }

    private void waitForLock(){

        try {
            Thread.sleep(new Random().nextInt(10)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
