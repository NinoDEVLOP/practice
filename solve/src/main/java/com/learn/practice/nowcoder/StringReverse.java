package com.learn.practice.nowcoder;

import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/13 11:51
 */
public class StringReverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            for (int i = str.length() -1; i >= 0; i--) {
                System.out.print(str.charAt(i));
                if (i == 0) {
                    System.out.print("\n");
                }
            }
        }
    }


}
