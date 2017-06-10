package com.open.acm.poj.p_1000;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: wsg
 * @Tel: 18337101865
 * @Email: shangangwu@qq.com
 * @Date: 2017/6/10 0:26
 */
public class No_1001 {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            HighAccuratePower highAccuratePower = null;
            while (in.hasNext()) {
                highAccuratePower = new HighAccuratePower(in.nextLine());
                highAccuratePower = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//高精度幂
class HighAccuratePower {

    //常数、乘数
    List<Integer> constants = new ArrayList<Integer>();

    //指数
    private int index = 0;

    //小数位
    private int decimalPlace = 0;

    //乘积
    private List<Integer> product = null;

    public HighAccuratePower(String param) {
        if (param == null) {
            return;
        }

        while (param.indexOf("  ") != -1) {
            param = param.replaceAll("  ", " ");
        }

        init(param.split(" "));
    }

    private void init(String[] res) {
        if (res.length != 2) {
            return;
        }

        char[] multipliers = res[0].toCharArray();
        decimalPlace = utils.parseString(multipliers, res[0].indexOf(".") != -1);

        multipliers = (new String(multipliers).replaceAll("#", "").replaceAll("@", ""))
                .toCharArray();
        for (int i =  0;i < multipliers.length;i++) {
            constants.add(multipliers[i] - 48);
        }

        index = Integer.parseInt(res[1].replace(" ", ""));
        decimalPlace = decimalPlace * index;

        utils.arrayTranspose(constants);
        product = new ArrayList<Integer>();
        for (Integer constant : constants) {
            product.add(constant);
        }

        calculation();
        print();
    }

    //计算
    private void calculation() {

        //被乘数
        List<BigFaciend> bigFaciends = null;

        for (int count = 1;count < index;count++) {
            bigFaciends = new ArrayList<BigFaciend>();
            for (int i = 0; i < constants.size(); i++) {
                BigFaciend bigFaciend = new BigFaciend(product, constants.get(i), i);
                bigFaciends.add(bigFaciend);
            }
            product = null;
            for (BigFaciend bigFaciend : bigFaciends) {
                product = utils.bigSum(product, bigFaciend.getProduct());
            }
        }

        utils.arrayTranspose(product);
    }

    /**
     * 输出目标值
     */
    private void print() {
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
        utils.parseString(tarchar, tar.indexOf(".") != -1);

        String tarnum = (new String(tarchar).replaceAll("#", "").replaceAll("@", "."));
        System.out.println(tarnum);
    }
}

//大数-被乘数
class BigFaciend {
    //常数、乘数
    private List<Integer> constants = null;
    //被乘数
    private int faciend = 0;
    //权重
    private int weight = 0;
    //乘积
    private List<Integer> product = null;

    public BigFaciend(List<Integer> constants, int faciend, int weight) {
        this.constants = constants;
        this.faciend = faciend;
        this.weight = weight;
        calculation();
    }

    //计算
    private void calculation() {
        product = utils.bigMultiplication(constants, faciend, weight);
    }

    public List<Integer> getProduct() {
        return product;
    }
}

class utils {
    /**
     * 解析大数char[]数组
     * 无效数字零"0"转换成"#"，小数点"."转换成"@"。
     * @param res 大数字符数组
     * @param decimal 是小数
     * @return 大数小数位数
     */
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

    /**
     * 大数相乘
     * @param constants 乘数
     * @param faciend 被乘数
     * @param weight 权重
     * @return 乘积
     */
    public static List<Integer> bigMultiplication(
            List<Integer> constants, int faciend, int weight) {
        List<Integer> products = new ArrayList<Integer>();

        if (faciend == 0) {
            return products;
        }
        for (int i = 0;i < weight;i++) {
            products.add(0);
        }

        //积
        int product;
        //余数
        int remainder = 0;

        for (Integer constant : constants) {
            product = constant * faciend + remainder;
            products.add(product % 10);
            remainder = product / 10;
        }

        while (remainder > 0) {
            products.add(remainder % 10);
            remainder = remainder / 10;
        }

        return products;
    }

    /**
     * 大数相加
     * @param add 加数
     * @param aug 被加数
     * @return 和
     */
    public static List<Integer> bigSum(List<Integer> add, List<Integer> aug) {
        List<Integer> sums = new ArrayList<Integer>();
        if (add == null && aug == null) {
            return sums;
        } else if ((add == null || add.size() == 0) && aug != null) {
            return aug;
        } else if (add != null && (aug == null || aug.size() == 0)) {
            return add;
        }

        //和
        int sum = 0;
        //进位数
        int carryNnum = 0;

        for (int i = 0;i < add.size() || i < aug.size();i++) {
            sum = carryNnum +
                    (i < add.size() ? add.get(i) : 0) +
                    (i < aug.size() ? aug.get(i) : 0);

            sums.add(sum % 10);
            carryNnum = sum / 10;
        }

        while (carryNnum > 0) {
            sums.add(carryNnum % 10);
            carryNnum = carryNnum / 10;
        }

        return sums;
    }

    /**
     * 数组转置
     */
    public static void arrayTranspose(List<Integer> products) {
        for (int i = 0, j = products.size() - 1;i < j;i++,j--) {
            products.set(i, products.get(i) + products.get(j));
            products.set(j, products.get(i) - products.get(j));
            products.set(i, products.get(i) - products.get(j));
        }
    }
}