package com.test.nowcoder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/14 21:37
 */
public class LetterSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            char[] chars = input.toCharArray();
            ArrayList<Character> collection = new ArrayList<>();
            for (char a: chars) {
                if (Character.isLetter(a)){
                    collection.add(a);
                }
            }
            collection.sort(new Comparator<Character>() {
                @Override
                public int compare(Character o1, Character o2) {
                    return Character.toLowerCase(o1) - Character.toLowerCase(o2);
                }
            });
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (char a : chars) {
                if (Character.isLetter(a)) {
                    sb.append(collection.get(index));
                    index++;
                } else {
                    sb.append(a);
                }
            }
            System.out.println(sb.toString());
        }
    }
}
