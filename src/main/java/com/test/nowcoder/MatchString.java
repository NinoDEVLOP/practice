package com.test.nowcoder;

import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/14 22:13
 */
public class MatchString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String pattern = scanner.nextLine().trim().replaceAll("[\\*]{2,}", "\\*");
            String target = scanner.nextLine().trim();
            int patternLength = pattern.length();
            int targetLength = target.length();
            int result = 1;
            for (int i = 0, j = 0; i < targetLength; i++) {
                if (pattern.charAt(j) == '*') {
                    if (j == patternLength - 1) {
                        break;
                    }
                    if (pattern.charAt(j + 1) == target.charAt(i)) {
                        j += 2;
                        if (i == targetLength - 1) {
                            break;
                        }
                    }
                    continue;
                }
                if (pattern.charAt(j) == '?') {
                    if (j == patternLength - 1 && i == targetLength - 1) {
                        break;
                    }else if (j != patternLength - 1 && i != targetLength - 1) {
                        j++;
                        continue;
                    }
                    result = 0;
                    break;
                }
                if (pattern.charAt(j) != target.charAt(i)) {
                    result = 0;
                    break;
                }
                j++;
            }
            System.out.println(result);
        }
    }


}
