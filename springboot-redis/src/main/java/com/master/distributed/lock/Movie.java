package com.master.distributed.lock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-10-31
 * Time: 10:07
 * info: 锁的应用测试示例
 */
public class Movie {

    public static CountDownLatch cdl = new CountDownLatch(1);

    //初始化电影票库存
    Integer movie_num = 8;

    //减库存
    public synchronized void reduce(int num){
        //判断可用库存操作
        if(movie_num - num >= 0){
            try {
                cdl.await();//线程阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            movie_num -= num;
            System.out.println(Thread.currentThread().getName() + "成功：卖出" + num +"张票，库存剩余" + movie_num.toString()+"张");
        }else{
            System.out.println(Thread.currentThread().getName() + "失败：库存不足" + num +"张票，库存剩余" + movie_num.toString()+"张");
        }
        
    }

    public void reduceLock(int num){
        boolean flag = false;

        //判断可用库存操作
        synchronized(movie_num){
            if(movie_num - num >= 0) {
                try {
                    cdl.await();//线程阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                movie_num -= num;
                flag = true;
            }
        }
        if(flag){
            System.out.println(Thread.currentThread().getName() + "成功：卖出" + num +"张票，库存剩余" + movie_num.toString()+"张");
        }else{
            System.out.println(Thread.currentThread().getName() + "失败：库存不足" + num +"张票，库存剩余" + movie_num.toString()+"张");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Movie movie = new Movie();
        for (int i=0; i <10; i++){
            new Thread(()->{
                movie.reduceLock(1);
            }, "用户" + i).start();
        }
        Thread.sleep(1000l);
        cdl.countDown();//10个线程同时去怒他
    }

}
