package com.open.acm.poj.p_1000;

import java.util.*;

/**
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/6/11 15:38
 */
public class No_1002 {
    private static Scanner in = new Scanner(System.in);
    private static StringBuilder msg = new StringBuilder("");
    private static Map<Integer, Integer> phoneNumberMap = new HashMap<Integer, Integer>();
    private static int[] phoneNumbers = new int[100000];
    public static void main(String[] args) {
        try {
            int count, phoneNumber, sum, length;
            while (in.hasNext()) {
                phoneNumberMap.clear();
                length = 0;
                count = in.nextInt();
                in.nextLine();
                for (int i = 0;i < count;i++) {
                    phoneNumber = getPhoneNumber();
                    sum = (phoneNumberMap.containsKey(phoneNumber) ?
                            phoneNumberMap.get(phoneNumber) : 0) + 1;

                    phoneNumberMap.put(phoneNumber, sum);
                    if (sum == 2) {
                        phoneNumbers[length++] = phoneNumber;
                    }
                }

                if (length == 0) {
                    msg.setLength(0);
                    msg.append("No duplicates.");
                    System.out.println(msg.toString());
                    continue;
                }

                quickSort(phoneNumbers, 0, length - 1);
                for (int i = 0;i < length;i++) {
                    pringTar(phoneNumbers[i], phoneNumberMap.get(phoneNumbers[i]));
                }
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    private static void pringTar(int phoneNumber, int count) {
        msg.setLength(0);
        msg.append(phoneNumber);
        for (int j = msg.length();j < 7;j++) {
            msg.insert(0, "0");
        }

        String tar = msg.toString();
        msg.setLength(0);
        msg.append(tar.substring(0, 3));
        msg.append("-");
        msg.append(tar.substring(3, 7));
        msg.append(" ").append(count);
        System.out.println(msg.toString());
    }

    private static int getPhoneNumber() {
        char[] phoneNumChars = in.nextLine().toCharArray();
        StringBuilder phoneNum = new StringBuilder("");
        for (char phoneNumChar : phoneNumChars) {
            switch (phoneNumChar) {
                case '1' : case '2' : case '3' :
                case '4' : case '5' : case '6' :
                case '7' : case '8' : case '9' : case '0' :
                    break;
                case 'A' : case 'B' : case 'C' :
                    phoneNumChar = '2'; break;
                case 'D' : case 'E' : case 'F' :
                    phoneNumChar = '3'; break;
                case 'G' : case 'H' : case 'I' :
                    phoneNumChar = '4'; break;
                case 'J' : case 'K' : case 'L' :
                    phoneNumChar = '5'; break;
                case 'M' : case 'N' : case 'O' :
                    phoneNumChar = '6'; break;
                case 'P' : case 'R' : case 'S' :
                    phoneNumChar = '7'; break;
                case 'T' : case 'U' : case 'V' :
                    phoneNumChar = '8'; break;
                case 'W' : case 'X' : case 'Y' :
                    phoneNumChar = '9'; break;
                default :
                    phoneNumChar = ' '; break;
            }
            if (phoneNumChar != ' ') {
                phoneNum.append(phoneNumChar);
            }
        }
        return phoneNum.length() > 0 ?
                Integer.parseInt(phoneNum.toString()) : 0;
    }

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
