package com.learn.practice.nowcoder;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author LittlePart
 * @version 1.0
 * @date 2020/03/2020/3/15 9:39
 */
public class ReportNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int people = 100;
            int outNum = sc.nextInt();
            if (outNum <= 1 || outNum >= 100) {
                System.out.println("ERROR!");
                continue;
            }
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 1; i < people + 1; i++) {
                list.add(i);
            }
            for (int i = 0; list.size() >= outNum;) {
                int temp = i + outNum - 1;
                if (temp < list.size()) {
                    list.remove(temp);
                    i = temp;
                } else {
                    i = temp - list.size() - outNum + 1;
                }
            }
//            for (int i = 0, j = 1; j < outNum + 1; j++) {
//                if (j % outNum == 0) {
//                    list.remove(i);
//                    j = 0;
//                    i--;
//                }
//                if (i == list.size() - 1) {
//                    if (list.size() < outNum) {
//                        break;
//                    }
//                    i = 0;
//                    continue;
//                }
//                i++;
//            }

            Integer last = list.get(list.size() - 1);
            for (Integer num : list) {
                System.out.print(num);
                if (!num.equals(last)) {
                    System.out.print(",");
                } else {
                    System.out.println();
                }
            }
        }
    }

}
