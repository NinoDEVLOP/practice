package com.learn.practice.nowcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/14 10:40
 */
public class DictionarySort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wordsCount = scanner.nextInt();
        String[] words = new String[wordsCount];
        for (int i = 0; i < wordsCount; i++) {
            words[i] = scanner.next();
        }
        Arrays.sort(words, (firstWord, nextWord) -> {
            int firstWordLength = firstWord.length();
            int nextWordLength = nextWord.length();
            int maxLength = Math.max(firstWordLength, nextWordLength);
            int minLength = Math.min(firstWordLength, nextWordLength);
            for (int i = 0; i < maxLength; i++) {
                if (i > firstWordLength - 1) {
                    return -1;
                } else if (i > nextWordLength - 1) {
                    return 1;
                }
                if (firstWord.charAt(i) < nextWord.charAt(i)) {
                    return -1;
                } else if (firstWord.charAt(i) == nextWord.charAt(i)) {
                    if (i != minLength - 1) {
                        continue;
                    }
                    return 0;
                } else if (firstWord.charAt(i) > nextWord.charAt(i)) {
                    return 1;
                }
            }
            return 0;
        });
        for (int i = 0; i < wordsCount; i++) {
            System.out.println(words[i]);
        }
    }

}
