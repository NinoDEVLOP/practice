package com.test.nowcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/13 13:48
 */
public class StringCombineConvert {

    /**
     * A->5 B->D  C->3  D->B E->7 F->F
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String words1 = scanner.next();
            String words2 = scanner.next();
            String words = sort(words1 + words2);
            words = convert(words);
            System.out.println(words);
        }

    }

    public static String sort(String words) {
        char data1[] = new char[words.length()];
        char data2[] = new char[words.length()];
        StringBuilder sb = new StringBuilder();
        int j = 0;
        int m = 0;
        for (int i = 0; i < words.length(); i++) {
            if ((1 & i) == 0) {
                data1[j++] = words.charAt(i);
            } else {
                data2[m++] = words.charAt(i);
            }
        }
        Arrays.sort(data1);
        Arrays.sort(data2);
        for (int i = 0; i < words.length(); i++) {
            sb.append((i % 2 == 0) ? data1[m++] : data2[j++]);
        }
        return sb.toString();
    }

    public static String convert(String words) {
        char[] chars = words.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            String s = String.valueOf(chars[i]);
            if (!s.matches("[0-9A-Fa-f]")) {
                sb.append(s);
                continue;
            }
            int a = Integer.parseInt(s, 16);
            a = a & 0xf;
            a = ((a & 0x5) << 1) | ((a & 0xa) >> 1);
            a = ((a & 0x3) << 2) | ((a & 0xc) >> 2);
            sb.append(Integer.toHexString(a).toUpperCase());
        }
        return sb.toString();
    }

}
