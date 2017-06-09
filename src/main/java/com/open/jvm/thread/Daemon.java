package com.open.jvm.thread;

/**
 * Java线程：线程的调度-守护线程
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/4/23 10:22
 */
public class Daemon {

    public static void main(String[] args) {
        Thread t1 = new MyCommon();
        Thread t2 = new Thread(new MyDaemon());
        t2.setDaemon(true);        //设置为守护线程

        t2.start();
        t1.start();
    }

}

class MyCommon extends Thread {
    public void run() {
        for (int i = 0; i < 500; i++) {
            System.out.println("线程1第" + i + "次执行！");
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyDaemon implements Runnable {
    public void run() {
        for (long i = 0; i < 9999999L; i++) {
            System.out.println("后台线程第" + i + "次执行！");
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}