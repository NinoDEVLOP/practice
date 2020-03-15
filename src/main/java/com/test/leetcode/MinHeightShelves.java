package com.test.leetcode;

import java.util.Arrays;

/**
 * @author xiaqingchuan
 * @version 1.0
 * @date 2020/01/2020/1/15 13:40
 */
public class MinHeightShelves {

    static class AloneSolution {

        public int minHeightShelves(int[][] books, int shelf_width) {
            // 怎么写都不对 还是看看答案找找感觉

            return 0;
        }

        private int minHeight() {
            return 0;
        }

    }

    static class TryFastAnswerSolution {

    }

    // leetcodeUser: ccnuacmhdu
    static class FastSolution {

        private int[] dp;
        private int[][] books;
        private int shelf_width;

        public int minHeightShelves(int[][] books, int shelf_width) {
            dp = new int[books.length];
            this.books = books;
            this.shelf_width = shelf_width;
            return find(0);
        }

        // 从i到books.length-1所得最小高度
        // 每一层中每个k到books.length-1所得最小高度中的最小值就是该层搞定的最小值，逐层搞定

//        for (int i = 0; i < dp.length; i++) {//循环遍历前n本书的最小高度
//            int curWidth = 0, tempIndex = i, highest = 0;
//            while (tempIndex > 0) {//当前的书越多回溯遍历的次数越多
//                curWidth += books[tempIndex - 1][0];
//                if (curWidth > shelf_width) {//额外条件，当回溯时，书的厚度超过书架宽度时，将开始下一本书的回溯
//                    break;
//                }
//                highest = Math.max(highest, books[tempIndex - 1][1]);//记录此层书架最高的书
//                dp[i] = Math.min(dp[i], dp[tempIndex - 1] + highest);//前n本书的最小高度
//                tempIndex -= 1;
//            }
//        }
        private int find(int i) {
            if (i >= books.length) {
                return 0;
            }
            if (dp[i] != 0) {
                return dp[i];
            }
            int highest = 0;
            int remainsWidth = shelf_width;
            int res = 1000 * 1000;
            for (int k = i; k < books.length; k++) {//遍历从第k本书开始，往后的遍历后，记录最小书架高度
                remainsWidth -= books[k][0]; //剩余书架宽度,假设新增一本好为书架宽度
                if (remainsWidth < 0) {      //
                    break;
                }
                highest = Math.max(highest, books[k][1]);
                res = Math.min(res, find(k + 1) + highest);
            }
            return dp[i] = res;
        }

    }

    static class FastSolution1 {

    }

    // leetcode: 刘岳
    static class MostDetailedSolution {

//        def minHeightShelves(self, books: List[List[int]], shelf_width: int) -> int:
//            n = len(books)
//            dp = [1000000] * (n + 1)
//            dp[0] = 0
//            for i in range(1, n + 1):
//                tmp_width, j, h = 0, i, 0
//                while j > 0:
//                    tmp_width += books[j - 1][0]
//                    if tmp_width > shelf_width:
//                        break
//                    h = max(h, books[j - 1][1])
//                    dp[i] = min(dp[i], dp[j - 1] + h)
//                    j -= 1
//            return dp[-1]
        public int minHeightShelves(int[][] books, int shelf_width) {
            int n = books.length;
            int[] dp = new int[n + 1];
            Arrays.fill(dp, 1000000);
            dp[0] = 0;
            for (int i = 0; i < dp.length; i++) {
                int curWidth = 0, tempIndex = i, highest = 0;
                while (tempIndex > 0) {
                    curWidth += books[tempIndex - 1][0];
                    if (curWidth > shelf_width) {
                        break;
                    }
                    highest = Math.max(highest, books[tempIndex - 1][1]);
                    dp[i] = Math.min(dp[i], dp[tempIndex - 1] + highest);
                    tempIndex -= 1;
                }
            }

            return dp[dp.length - 1];
        }

    }

    public static void main(String[] args) {
        FastSolution solution = new FastSolution();
        int[][] books = new int[][]{{1, 1}, {2, 3}, {2, 3}/* , {1, 1}, {1, 1}, {1, 1}, {1, 2} */};
        System.out.println(solution.minHeightShelves(books, 4));

    }

}
