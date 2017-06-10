package com.open.acm.poj.p_1000;

import java.util.Scanner;

/**
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/6/10 0:21
 */
public class No_1000 {
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
}
