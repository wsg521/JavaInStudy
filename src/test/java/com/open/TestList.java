package com.open;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wusg
 * Email wushangang@jianbaolife.com/1210460667@qq.com
 * On 2017/6/13 0013.
 */
public class TestList {

    public static void main(String[] args) {

        List list = null;
        System.out.println(list == null || list.isEmpty());
        list = new ArrayList();
        System.out.println(list == null || list.isEmpty());
        list.add(1);
        System.out.println(list == null || list.isEmpty());

    }

}
