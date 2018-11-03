package com.master.distributed.lock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-10-31
 * Time: 10:07
 * info: 锁的应用测试示例
 */
public class Film {

    public static CountDownLatch cdl = new CountDownLatch(1);

    //复仇者联盟3
    private Integer fuTicket = 20;

    //侏罗纪公园2
    private Integer juclassTicket = 100;

    public void fuTicket() throws InterruptedException {
        synchronized (fuTicket) {
            System.out.println("复仇者联盟3剩余票数 = " + fuTicket);
            cdl.await();
        }
    }

    public void juclassTicket() throws InterruptedException {
        synchronized (juclassTicket) {
            System.out.println("侏罗纪公园2剩余票数 = " + juclassTicket);
            cdl.await();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Film film = new Film();
        new Thread(() -> {
            try {
                film.fuTicket();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                film.juclassTicket();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000l);
        //cdl.countDown();
    }

}
