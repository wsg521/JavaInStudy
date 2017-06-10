package com.open.acm.poj.p_1000;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/6/11 4:19
 */
public class No_1001_major02 {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            while (in.hasNext()) {
                String param = in.nextLine();
                if (param == null) {
                    return;
                }
                while (param.contains("  ")) {
                    param = param.replaceAll("  ", " ");
                }

                execute(param.split(" "));
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    private static void execute(String[] res) {
        char[] multipliersChar = res[0].toCharArray();
        int decimalPlace = parseString(multipliersChar, res[0].contains("."));
        multipliersChar = (new String(multipliersChar).replaceAll("#", "").replaceAll("@", ""))
                .toCharArray();
        List<Integer> multipliers = new ArrayList<Integer>();
        for (char c : multipliersChar) {
            multipliers.add(c - 48);
        }
        int index = Integer.parseInt(res[1].replace(" ", ""));
        decimalPlace = decimalPlace * index;
        arrayTranspose(multipliers);
        List<Integer> faciends = new ArrayList<Integer>();
        for (Integer multiplier : multipliers) {
            faciends.add(multiplier);
        }

        calculation(multipliers, index, faciends, decimalPlace);
    }

    private static void calculation(List<Integer> multipliers,
                                    int index, List<Integer> faciends, int decimalPlace) {
        List<Integer> monomialProduct;
        int product;
        int remainder;
        boolean isT;
        for (int count = 1;count < index;count++) {
            monomialProduct = new ArrayList<Integer>();
            for (int i = 0;i < multipliers.size();i++) {
                remainder = 0;
                for (int j = 0;j < faciends.size();j++) {
                    isT = (i + j) < monomialProduct.size();
                    product = (multipliers.get(i) * faciends.get(j)) + remainder +
                            (isT ? monomialProduct.get(i + j) : 0);
                    if (isT) {
                        monomialProduct.set(i + j, product % 10);
                    } else {
                        monomialProduct.add(product % 10);
                    }
                    remainder = product / 10;
                }
                int offSet = faciends.size() + 1;
                while (remainder > 0) {
                    isT = (i + offSet) < monomialProduct.size();
                    if (isT) {
                        monomialProduct.set(i + offSet++, remainder % 10);
                    } else {
                        monomialProduct.add(remainder % 10);
                    }
                    remainder = remainder / 10;
                }
            }
            faciends = monomialProduct;
        }

        arrayTranspose(faciends);
        print(faciends, decimalPlace);
    }

    private static void print(List<Integer> product, int decimalPlace) {
        StringBuilder target = new StringBuilder("");
        for (Integer integer : product) {
            target.append(integer);
        }
        if (decimalPlace > 0) {
            int offset = product.size() - decimalPlace;
            if (offset > 0) {
                target.insert(offset, ".");
            } else if (offset < 0) {
                for (int i = 0; i < -offset; i++) {
                    target.insert(0, "0");
                }
                target.insert(0, ".");
            }
        }

        String tar = target.toString();
        char[] tarchar = tar.toCharArray();
        parseString(tarchar, tar.contains("."));

        String tarnum = (new String(tarchar).replaceAll("#", "").replaceAll("@", "."));
        System.out.println(tarnum);
    }

    public static int parseString(char[] res, boolean decimal) {
        for (int i = 0;i < res.length;) {
            if (res[i] == '0') {
                res[i++] = '#';
            } else {
                break;
            }
        }

        boolean decline = true;
        int decimalPlace = 0;
        for (int j = res.length - 1;decimal && j >= 0;j--) {
            if ((res[j] == '0' || res[j] == ' ') && decline) {
                res[j] = '#';
                decimalPlace--;
            } else if (res[j] == '.') {
                res[j] = '@';
                decimalPlace += res.length - j - 1;
                break;
            } else {
                decline = false;
            }
        }

        return decimalPlace;
    }

    public static void arrayTranspose(List<Integer> products) {
        for (int i = 0, j = products.size() - 1;i < j;i++,j--) {
            products.set(i, products.get(i) + products.get(j));
            products.set(j, products.get(i) - products.get(j));
            products.set(i, products.get(i) - products.get(j));
        }
    }
}