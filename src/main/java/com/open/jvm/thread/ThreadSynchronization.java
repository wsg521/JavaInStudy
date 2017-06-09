package com.open.jvm.thread;

/**
 * Java线程：线程的同步
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/4/23 11:55
 */
public class ThreadSynchronization {

    //线程同步类型：1、方法同步；2、代码块同步；
    protected static int synchronizedType = 1;

    public static void main(String[] args) {
        User u = new User("张三", 100);
        MyThread t1 = new MyThread("线程A", u, 20);
        MyThread t2 = new MyThread("线程B", u, -60);
        MyThread t3 = new MyThread("线程C", u, -80);
        MyThread t4 = new MyThread("线程D", u, -30);
        MyThread t5 = new MyThread("线程E", u, 32);
        MyThread t6 = new MyThread("线程F", u, 21);

        synchronizedType = 2;

        System.out.println("原有资金：" + u.getCash() + "万元") ;

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

}

class User {

    private String code;
    private int cash;

    public User(String code, int cash) {
        this.code = code;
        this.cash = cash;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    /**
     * Java线程：线程的同步-同步方法
     * 业务方法
     * @param x 添加x万元
     */
    public synchronized void oper(int x) {
        try {
            Thread.sleep(10L);
            this.cash += x;
            System.out.println(Thread.currentThread().getName() + ", 同步方法 - " +
                    "运行结束，增加“" + x + "”万元，当前用户账户余额为：" + cash);
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Java线程：线程的同步-同步代码块
     * 业务方法
     * @param x 添加x万元
     */
    public void operBlock(int x) {
        try {
            Thread.sleep(10L);
            synchronized (this) {
                this.cash += x;
                System.out.println(Thread.currentThread().getName() + ", 同步代码块 - " +
                        "运行结束，增加“" + x + "”万元，当前用户账户余额为：" + cash);
            }
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "code='" + code + '\'' +
                ", cash=" + cash +
                '}';
    }
}

class MyThread extends Thread {
    private User u;
    private int y = 0;

    MyThread(String name, User u, int y) {
        super(name);
        this.u = u;
        this.y = y;
    }

    public void run() {
        int synType = ThreadSynchronization.synchronizedType;
        if (synType == 1) {
            u.oper(y);
        } else if (synType == 2) {
            u.operBlock(y);
        } else {
            System.out.println("无对应线程同步类型");
        }
    }
}
