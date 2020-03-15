package com.test.nowcoder;

import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/14 20:30
 */
public class BinOneCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int count = 0;
        while (i != 0) {
            if ((1 & i) == 1) {
                count++;
            }
            i = i >>> 1;
        }
        System.out.println(count);
    }

}
