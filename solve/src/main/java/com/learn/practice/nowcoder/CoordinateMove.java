package com.learn.practice.nowcoder;

import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/14 20:42
 */
public class CoordinateMove {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //while (scanner.hasNext()) {
            String str = scanner.next().trim();
            String[] moves = str.split(";");
            int x = 0;
            int y = 0;
            for (String moving : moves) {
                if (moving.length() == 0) {
                    continue;
                }
                char mode = moving.charAt(0);
                String digit = moving.substring(1);
                if (!digit.matches("[0-9]+")) {
                    continue;
                }
                if (mode == 'A') {
                    x -= Integer.parseInt(digit);
                } else if (mode == 'D') {
                    x += Integer.parseInt(digit);
                } else if (mode == 'W') {
                    y += Integer.parseInt(digit);
                } else if (mode == 'S') {
                    y -= Integer.parseInt(digit);
                }
            }
            System.out.println(x + "," + y);
       // }
    }

}
