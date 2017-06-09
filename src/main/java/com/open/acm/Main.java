package com.open.acm;

import java.util.Scanner;

/**
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/6/9 23:30
 */
public class Main {
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
