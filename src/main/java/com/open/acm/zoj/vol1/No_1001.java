package com.open.acm.zoj.vol1;

import java.util.Scanner;

/**
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/6/2 21:55
 */
public class No_1001 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = 0;
        int b = 0;
        while (in.hasNextInt()) {
            a = in.nextInt();
            b = in.nextInt();
            System.out.println(a + b);
        }
    }

    private void test() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}
