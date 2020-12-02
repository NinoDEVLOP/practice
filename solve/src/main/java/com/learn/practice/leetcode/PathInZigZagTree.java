package com.learn.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiaqingchuan
 * @version 1.0
 * @date 2020/01/2020/1/15 10:56
 */
public class PathInZigZagTree {

    static class AloneSolution {
        private List<Integer> list;

        public List<Integer> pathInZigZagTree(int label) {
            String bit = Integer.toBinaryString(label);
            int floor = bit.length();
            if (list == null) {
                list = new ArrayList<>(floor);
            }
            this.pathInteger(label);
            return list;
        }

        private void pathInteger(Integer label) {
            int floor = Integer.toBinaryString(label).length();
            if (floor != 1) {
                int floorElement = 1 << (floor - 1);
                int lastFloorOffset = (label % floorElement) >> 1;
                int lastLabel = floorElement - 1 - lastFloorOffset;
                pathInteger(lastLabel);
            }
            list.add(label);
        }
    }

    static class TryFastAnswerSolution {

        public List<Integer> pathInZigZagTree(int label) {
            int floor = 0;
            int tempLabel = label;
            do {
                tempLabel = tempLabel >> 1;
                floor++;
            } while (tempLabel != 0);
            Integer[] result = new Integer[floor];
            tempLabel = label;
            result[floor - 1] = label;
            for (int i = floor; i > 1; i--) {
                int floorElement = 1 << (i - 1);
                int lastFloorOffset = (tempLabel % floorElement) >> 1;
                tempLabel = floorElement - 1 - lastFloorOffset;
                result[i - 2] = tempLabel;
            }
            return Arrays.asList(result);
        }
    }

    static class FastSolution {
        public List<Integer> pathInZigZagTree(int label) {
            int depth = 1;
            int tempLabel = label;
            tempLabel = tempLabel >> 1;
            while (tempLabel != 0) {
                depth++;
                tempLabel = tempLabel >> 1;
            }
            int k = depth % 2 == 0 ? (1 << depth) - label : (label - (1 << (depth - 1)) + 1);
            Integer[] result = new Integer[depth];
            result[depth - 1] = label;
            for (int i = depth - 1; i > 0; i--) {
                if (i == 1) {
                    result[0] = 1;
                } else {
                    k = (k + 1) / 2;
                    int num = i % 2 == 0 ? (1 << i) - k : ((1 << (i - 1)) + k - 1);
                    result[i - 1] = num;
                }
            }
            return Arrays.asList(result);
        }
    }

    public static void main(String[] args) {
        AloneSolution solution = new AloneSolution();
        System.out.println(solution.pathInZigZagTree(26));
    }

}
