package com.learn.practice.nowcoder;

import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/13 11:21
 */
public class DrinkSoda {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int emptyBottle = scanner.nextInt();
            if (emptyBottle == 0) {
                break;
            }
            System.out.println(couldDrink(emptyBottle));
        }
    }

    private static int couldDrink(int emptyBottle) {
        int now = emptyBottle / 3;
        int remainder = emptyBottle % 3;
        if (now == 0) {
            return remainder == 2 ? 1 : 0;
        }
        return now + couldDrink(now + remainder);
    }
}
