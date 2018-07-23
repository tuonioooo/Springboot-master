package com.master.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Service
public class UserService {
	
	private Map<Long, String> dataMap = new HashMap<>();
	
	@Autowired
	private EhCacheCacheManager ehCacheCacheManager;
	
	/** 
     * 初始化 
     */  
    @PostConstruct  
    public void init() {  
        dataMap.put(1L, "张三");  
        dataMap.put(2L, "李四");  
        dataMap.put(3L, "王五");  
    }

    /**
     * 查询
     * @param id
     * @return
     */
	@Cacheable(value="ddf" ,key="#id + 'dataMap'")  
    public String query(Long id) {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        System.out.println(sdf.format(new Date()) + " : query id is " + id);  
        System.out.println(dataMap);
        return dataMap.get(id);  
    }
	
	 /** 
     * 插入或者更新
     */  
    @CachePut(value="ddf" ,key="#id + 'dataMap'")  
    public String put(Long id, String value) { 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        System.out.println(sdf.format(new Date()) + " : add data ,id is "+ id);  
        dataMap.put(id, value);
        System.out.println(dataMap);
        return value;  
    }
    
    /** 
     * 插入 或者更新 
     * 插入或更新数据到dataMap中 
     * 并且缓存到 guavaDemo中 
     * 如果存在了那么更新缓存中的值 
     * 其中key 为 #id+dataMap 
     */   
    public String put2(Long id, String value) { 
    	CacheManager cacheManager = ehCacheCacheManager.getCacheManager();
		Cache cache = cacheManager.getCache("iseCache");
		Element element = cache.get("#id + 'dataMap'");
		if(element!=null){
			return element.getObjectValue().toString();
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        System.out.println(sdf.format(new Date()) + " : add data ,id is "+ id);
			cache.put(new Element("#id + 'dataMap'", value));
			return value;
		}
    }
    
    /** 
     * 删除
     */  
    @CacheEvict(value="ddf" , key="#id + 'dataMap'")  
    public void remove(Long id) {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        System.out.println(sdf.format(new Date()) + " : remove id is "+ id + " data");  
        dataMap.remove(id);
        System.out.println(dataMap);
        // data remove    
    }  

}
