package com.test.leetcode;

/**
 * @author xiaqingchuan
 * @version 1.0
 * @date 2020/01/2020/1/15 13:40
 */
public class MinHeightShelves {

    static class AloneSolution {
        public int minHeightShelves(int[][] books, int shelf_width) {
            //怎么写都不对 还是看看答案找找感觉
            return 0;
        }
    }

    static class TryFastAnswerSolution {


    }

    static class FastSolution {


    }

    public static void main(String[] args) {
        AloneSolution solution = new AloneSolution();
        int[][] books = new int[][]{{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}};
        System.out.println(solution.minHeightShelves(books, 4));
    }

}
