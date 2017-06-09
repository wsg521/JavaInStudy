package com.open.jvm.thread;

/**
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/4/22 15:51
 */
public class DeadlockRisk {

    public static Book book = new Book();

    public static void main(String[] args) {

        WriteThread writeThread = new WriteThread("writeThread");
        ReadThread readThread = new ReadThread("readThread");

        writeThread.start();
        readThread.start();
    }
}

class WriteThread extends Thread {

    public WriteThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            while (true) {
                DeadlockRisk.book.write(1, 3);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ReadThread extends Thread {

    public ReadThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            while (true) {
                DeadlockRisk.book.read();
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Book {
    private static class Resource {
        public int value;
    }

    private Resource resourceA = new Resource();
    private Resource resourceB = new Resource();

    public void read() {
        System.out.println(Thread.currentThread().getName() + ": read : 等待 resourceA 锁");
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + ": read : 等待 resourceB 锁");
            synchronized (resourceB) {
                System.out.println("read : " + (resourceB.value + resourceA.value));
            }
        }
    }

    public void write(int a, int b) {
        System.out.println(Thread.currentThread().getName() + ": write : 等待 resourceB 锁");
        synchronized (resourceB) {
            System.out.println(Thread.currentThread().getName() + ": write : 等待 resourceA 锁");
            synchronized (resourceA) {
                resourceA.value = a;
                resourceB.value = b;
                System.out.println("write : " + (resourceB.value + resourceA.value));
            }
        }
    }
}