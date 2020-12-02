package com.learn.practice.nowcoder;

import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/13 9:14
 */
public class TrueFalseExpress {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String param;
        while (!scanner.hasNext()) {
        }
        param = scanner.nextLine();
        String[] words = param.split(" ");
        try {
            boolean result = exam(words, 0, words.length);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println("error");
        }
    }

    private static boolean exam(String[] words, int begin, int end) {
        boolean last = true;
        boolean lastIsConjunction = true;
        for (int i = begin; i < end; i++) {
            String cur = words[i];
            char first = cur.charAt(0);
            if (lastIsConjunction) {
                if ((first == 't' || first == 'f')) {
                    last = first == 't';
                    lastIsConjunction = false;
                } else {
                    throw new RuntimeException();
                }
            } else {
                if (i == words.length - 1) {
                    throw new RuntimeException();
                }
                if (first == 'o') {
                    last = last || exam(words, i + 1, words.length);
                } else if (first == 'a') {
                    last = last && exam(words, i + 1, words.length);
                } else {
                    throw new RuntimeException();
                }
                break;
            }
        }
        return last;
    }

}
