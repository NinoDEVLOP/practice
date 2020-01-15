package com.test.leetcode;

import java.util.Arrays;

/**
 * @author xiaqingchuan
 * @version 1.0
 * @date 2020/01/2020/1/13 11:03
 */
public class DistributeCandies {

    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int sum = 0, row = 0, last_row_sum = 0, last_row_current = 0;
        while (sum != candies) {
            sum += ((num_people * num_people + num_people) >> 1) + row * num_people * num_people;
            if (sum >= candies) {
                last_row_sum = candies - (sum - (((num_people * num_people + num_people) >> 1) + row * num_people * num_people));
                sum = candies;
                row--;
            } else {
                row++;
            }
        }
        for (int i = 0; i < num_people; i++) {
            if (last_row_current != last_row_sum) {
                last_row_current += ((row + 1) * num_people + i + 1);
                result[i] = (((i << 1) + 2 + num_people * (row + 1)) * (row + 2)) >> 1;
                if (last_row_current >= last_row_sum) {
                    result[i] = result[i] - (last_row_current - last_row_sum);
                    last_row_current = last_row_sum;
                }
            } else {
                result[i] = Math.max((((i << 1) + 2 + num_people * row) * (row + 1)) >> 1, 0);
            }
        }
        return result;
//        int[] candiesArr = new int[num_people];
//        int index = 0;
//        int count = 1;
//        while (candies > 0) {
//            candiesArr[(index++) % num_people] += Math.min(candies, count);
//            candies -= count++;
//        }
//        return candiesArr;


//        for (int i = 1, cyc = 0; i <= num_people; i++) {
//            if (candies > (i + cyc)) {
//                candiesArr[i - 1] += i + cyc;
//                candies -= i + cyc;
//            } else {
//                candiesArr[i - 1] += candies;
//                break;
//            }
//            if (i == num_people) {
//                i = 0;
//                cyc += num_people;
//            }
//        }
        //return candiesArr;
    }

    public static void main(String[] args) {
        DistributeCandies distributeCandies = new DistributeCandies();
        System.out.println(Arrays.toString(distributeCandies.distributeCandies(20, 3)));

    }

}
