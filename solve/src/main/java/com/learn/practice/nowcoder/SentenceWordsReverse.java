package com.learn.practice.nowcoder;

import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/13 13:17
 */
public class SentenceWordsReverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String sentence = scanner.nextLine();
            String[] words = sentence.split("[^a-zA-z]");
            StringBuilder sb = new StringBuilder();
            for (int i = words.length - 1; i >= 0; i--) {
                sb.append(words[i]).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }


}
