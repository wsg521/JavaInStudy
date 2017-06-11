package com.open.test;

import java.awt.*;
import java.awt.List;
import java.util.*;

/**
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/6/10 23:28
 */
public class test {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = map.containsKey("zhangsan") ? map.get("zhangsan") : 0;
        System.out.println(count);

        Set set = new HashSet();
        set.add(6);
        set.add(6);
        int[] num = new int[set.size()];
        int i = 0;
        for (Object o : set.toArray()) {
            num[i++] = (Integer) o;
        }
        System.out.println(set.size());
    }
}
