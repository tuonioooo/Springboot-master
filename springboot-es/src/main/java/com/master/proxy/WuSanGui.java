package com.master.proxy;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-8-22
 * Time: 11:55
 * info: 吴三桂
 */
public class WuSanGui {

    public static void main(String[] args) {
        //cglib动态代理实现
        TianHongCglib cglib = new TianHongCglib();
        ChenYuanYuan chenYuanYuan = (ChenYuanYuan) cglib.bind(new ChenYuanYuan());
        chenYuanYuan.happyWithMan();
    }
}
