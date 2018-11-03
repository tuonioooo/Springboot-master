package com.master.service;

import com.master.bean.Tgoodsinfo;
import com.master.dao.DaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
  *
  * @author tuonioooo
  *
  */
@Service("tgoodsinfoService")
public class TgoodsinfoService{

	private static Logger logger = LoggerFactory.getLogger(TgoodsinfoService.class);
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public int save(Tgoodsinfo tgoodsinfo){
		try {
			return (int) dao.save("TgoodsinfoMapper.save", tgoodsinfo);
		} catch (Exception e) {
			logger.error("TgoodsinfoMapper save exception", e);
			return 0;
		}
	}

	/**
	 * 数据库乐观锁基于状态实现并发控制
	 * @param tgoodsinfo
	 * @return
	 */
	public int updateOfStatus(Tgoodsinfo tgoodsinfo){
		try {
			return (int) dao.save("TgoodsinfoMapper.updateOfStatus", tgoodsinfo);
		} catch (Exception e) {
			logger.error("TgoodsinfoMapper updateOfStatus exception", e);
			return 0;
		}
	}

	/**
	 * 数据库乐观锁基于版本号实现并发控制
	 * @param tgoodsinfo
	 * @return
	 */
	public int updateOfVersion(Tgoodsinfo tgoodsinfo){
		try {
			return (int) dao.save("TgoodsinfoMapper.updateOfVersion", tgoodsinfo);
		} catch (Exception e) {
			logger.error("TgoodsinfoMapper updateOfVersion exception", e);
			return 0;
		}
	}

	public Tgoodsinfo get(String code){
		try {
			return (Tgoodsinfo) dao.findForObject("TgoodsinfoMapper.get", code);
		} catch (Exception e) {
			logger.error("TgoodsinfoMapper get exception", e);
			return null;
		}
	}

	public List<Tgoodsinfo> findAll(){
		try {
			return (List<Tgoodsinfo>) dao.findForList("TgoodsinfoMapper.findAll", null);
		} catch (Exception e) {
			logger.error("TgoodsinfoMapper findAll exception", e);
			return null;
		}
	}

	/**
	 *秒杀服务，修改库存（错误演示）
	 */
	public boolean updateGoodsAmout(String code, int buys){
		//获取商品库存对象
		Tgoodsinfo tgoodsinfo = get(code);
		//获取库存
		Integer amout = tgoodsinfo.getAmout();

		//如果库存不够直接返回
		if(amout < buys){
			return false;
		}
		//不加锁直接访问数据
		return this.updateAmout(code, buys) >0 ? true : false;

	}

	/**
	 *秒杀服务，修改库存（数据库乐观锁基于版本号实现并发控制——正确演示）
	 */
	public boolean updateGoodsAmout1(String code, int buys){
		//获取商品库存对象
		Tgoodsinfo tgoodsinfo = get(code);
		//获取库存
		Integer amout = tgoodsinfo.getAmout();

		//如果库存不够直接返回
		if(amout < buys){
			return false;
		}

		//获取版本号
		Integer version = tgoodsinfo.getVersion();

		//带上版本号更新库存
		//this.updateOfVersion(code, buys, version);

		tgoodsinfo.setBuys(buys);
		if(this.updateOfVersion(tgoodsinfo) > 0){
			return true;
		}

		//如果更新失败，当前线程休眠，措峰执行
		waitForLock();
		//递归调用本身
		return updateGoodsAmout1(code, buys);

	}

	/**
	 *秒杀服务，修改库存（数据库乐观锁基于状态实现并发控制——正确演示）
	 */
	public boolean updateGoodsAmout2(String code, int buys){
		//获取商品库存对象
		Tgoodsinfo tgoodsinfo = get(code);
		//获取库存
		Integer amout = tgoodsinfo.getAmout();

		//如果库存不够直接返回
		if(amout < buys){
			return false;
		}

		tgoodsinfo.setBuys(buys);
		if(this.updateOfStatus(tgoodsinfo) > 0){
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

	private int updateAmout(String code, int buys){
		try {
			Map map = new HashMap<>();
			map.put("code", code);
			map.put("buys", buys);
			return (int) dao.save("TgoodsinfoMapper.updateAmout", map);
		} catch (Exception e) {
			logger.error("TgoodsinfoMapper updateAmout exception", e);
			return 0;
		}
	}


	public void delete(Integer id){
		dao.delete("TgoodsinfoMapper.delete", id);
	}

	

}