package com.learn.practice.nowcoder;

import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/14 21:21
 */
public class PrimeFactorization {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            long digit = scanner.nextLong();
            while (digit != 1) {
                for (int i = 2; i <= digit; i++) {
                    if (digit % i == 0) {
                        digit /= i;
                        System.out.print(i + " ");
                        break;
                    }
                }
            }
        }
    }

}
