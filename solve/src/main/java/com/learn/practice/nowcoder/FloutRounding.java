package com.learn.practice.nowcoder;

import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/13 11:38
 */
public class FloutRounding {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String floatStr = scanner.nextLine();
            String[] digit = floatStr.split("\\.");
            int add;
            if (digit.length == 1) {
                add = 0;
            } else {
                String decimal = digit[1].substring(0, 1);
                add = Integer.parseInt(decimal) >= 5 ? 1 : 0;
            }
            System.out.println(Integer.parseInt(digit[0]) + add);
        }
    }

}
