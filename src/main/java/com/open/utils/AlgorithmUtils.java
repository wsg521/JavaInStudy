package com.open.utils;

/**
 * 算法工具类
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/6/11 15:41
 */
public class AlgorithmUtils {
    //快速排序
    private static void quickSort(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int povit = arr[low];

        while (l < h) {
            while (l < h && arr[h] >= povit) h--;
            if (l < h) {
                arr[l] += arr[h];
                arr[h] = arr[l] - arr[h];
                arr[l] = arr[l] - arr[h];
                l++;
            }

            while (l < h && arr[l] <= povit) l++;
            if (l < h) {
                arr[l] += arr[h];
                arr[h] = arr[l] - arr[h];
                arr[l] = arr[l] - arr[h];
                h--;
            }
        }

        if(l > low) {
            quickSort(arr, low, l - 1);
        }

        if(h < high) {
            quickSort(arr, l + 1, high);
        }
    }
}
