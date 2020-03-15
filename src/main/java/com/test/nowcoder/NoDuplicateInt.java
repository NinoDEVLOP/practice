package com.test.nowcoder;

import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/13 19:19
 */
public class NoDuplicateInt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String digit = scanner.next();
            int[] arr = new int[10];
            StringBuilder sb = new StringBuilder();
            for (int i = digit.length() - 1; i >= 0; i--) {
                int cur = Integer.parseInt(String.valueOf(digit.charAt(i)));
                if (arr[cur] == 0) {
                    sb.append(cur);
                    arr[cur] = 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

}
