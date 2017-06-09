package com.open.jvm.thread;

/**
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/4/22 15:16
 */
public class SynchronousLock implements Runnable {

    private Foo foo = new Foo();

    public static void main(String[] args) {
        SynchronousLock r = new SynchronousLock();
        Thread ta = new Thread(r, "Thread-A");
        Thread tb = new Thread(r, "Thread-B");
        ta.start();
        tb.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0;i < 3; i++) {
//                this.fix(30);
                this.lockFix(30);
                Thread.sleep(1);
                System.out.println(Thread.currentThread().getName() + " : 当前foo对象的x值= " +
//                        foo.getX());
                        foo.getLockX());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int fix(int y) {
        return foo.fix(y);
    }

    public int lockFix(int y) {
        return foo.lockFix(y);
    }

}

class Foo {

    private int x = 100;

    public int getX() {
        return x;
    }

    public int getLockX() {
        synchronized (this) {
            return x;
        }
    }

    public int fix(int y) {
        x = x - y;
        return x;
    }

    public int lockFix(int y) {
        synchronized (this) {
            x = x - y;
            return x;
        }
    }
}