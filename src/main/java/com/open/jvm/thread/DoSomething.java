package com.open.jvm.thread;

/**
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/4/22 10:42
 */
public class DoSomething implements Runnable {

    private String name;

    public DoSomething(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1;i <= 10;i++) {
            for (long k = 0;k < 100000000;k++);
            System.out.println(name + ": " + i);
        }
    }

    /**
     * 测试Runnable类实现的多线程程序
     */
    public static void main(String[] args) {
        try {
            DoSomething rw1 = new DoSomething("任务一");
            DoSomething rw2 = new DoSomething("任务二");

            Thread t11 = new Thread(rw1);
            Thread t12 = new Thread(rw2);

            MyDoSomething t22 = new MyDoSomething("主线程一");
            MyDoSomething t23 = new MyDoSomething("主线程二");

//            t22.start();
//            t22.join();
//            t23.start();
//            t11.start();
//            t11.yield();
            t11.join();
            t12.start();
            t11.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyDoSomething extends Thread {

    public MyDoSomething(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            DoSomething ds1 = new DoSomething(this.getName() + " : " + "线程一号");
            DoSomething ds2 = new DoSomething(this.getName() + " : " + "线程二号");
            DoSomething ds3 = new DoSomething(this.getName() + " : " + "线程三号");

            Thread t1 = new Thread(ds1);
            Thread t2 = new Thread(ds2);
            Thread t3 = new Thread(ds3);

            t1.start();
            t2.start();
            t3.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
