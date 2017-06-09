package com.open.jvm.thread;

/**
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/4/23 9:52
 */
public class WaitAndNotify {

    public static void main(String[] args) {
        SumCount sumCount = new SumCount();
        //启动计算线程
        sumCount.start();
        //线程A拥有b对象上的锁。线程为了调用wait()或notify()方法，该线程必须是那个对象锁的拥有者
        synchronized (sumCount) {
            try {
                System.out.println("等待对象b完成计算......");
                //当前线程A等待
                sumCount.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sumCount对象计算的总和是：" + sumCount.getTotal());
        }
    }

}

class SumCount extends Thread {

    private int total;

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("开始计算 total 的值......");
            for (int i = 1;i <= 100;i++) {
                total += i;
            }
            System.out.println("total 的值是:" + total);
            //（完成计算了）唤醒在此对象监视器上等待的单个线程，在本例中线程A被唤醒
//            notify();
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}