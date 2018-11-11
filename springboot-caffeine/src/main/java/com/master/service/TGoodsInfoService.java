package com.master.service;

import com.master.bean.TGoodsInfo;
import com.master.dao.TGoodsInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-11-8
 * Time: 17:33
 * info:
 */
@Service
public class TGoodsInfoService {

    @Autowired
    private TGoodsInfoRepository tGoodsInfoRepository;

    @Cacheable(value = "goodsInfo", key = "#id")
    public TGoodsInfo findGoods(int id){
        TGoodsInfo tGoodsInfo = tGoodsInfoRepository.findById(id);
        System.out.println("goodsInfo从db中取数据");
        return tGoodsInfo;
    }

    @Cacheable
    public TGoodsInfo getFindGoods(int id){
        TGoodsInfo tGoodsInfo = tGoodsInfoRepository.findById(id);
        System.out.println("getFindGoods从db中取数据");
        return tGoodsInfo;
    }


}
