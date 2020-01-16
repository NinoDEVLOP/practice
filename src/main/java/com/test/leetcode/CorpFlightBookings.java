package com.test.leetcode;

/**
 * @author xiaqingchuan
 * @version 1.0
 * @date 2020/01/2020/1/14 10:30
 */
public class CorpFlightBookings {

    static class AloneSolution {

        public int[] corpFlightBookings(int[][] bookings, int n) {
            // Map<Integer, Integer> map = new HashMap<>(n);
            int[] result = new int[n];
            // n*n*c
            for (int[] inner : bookings) {
                for (int j = inner[0]; j <= inner[1]; j++) {
                    result[j - 1] = result[j - 1] + inner[2];
                }
            }

            // 3ms
//	        int[] counters = new int[n];
//	        for (int[] booking : bookings) {
//	            counters[booking[0] - 1] += booking[2];
//	            if (booking[1] < n) {
//	                counters[booking[1]] -= booking[2];
//	            }
//	        }
//	        for (int i = 1; i < n; ++i) {
//	            counters[i] += counters[i - 1];
//	        }
//	        return counters;

            // n * c
//	        for (int i = 0; i < result.length; i++) {
//	            for (int[] inner : bookings) {
//	                if (inner[0] <= i + 1 && i + 1 <= inner[1]) {
//	                    result[i] = result[i] + inner[2];
//	                }
//	            }
//	        }
            return result;

        }

    }

    static class FastAnswer {

        public int[] corpFlightBookings(int[][] bookings, int n) {
            int[] counters = new int[n];
            for (int[] booking : bookings) {
                counters[booking[0] - 1] += booking[2];
                if (booking[1] < n) {
                    counters[booking[1]] -= booking[2];
                }
            }
            for (int i = 1; i < n; ++i) {
                counters[i] += counters[i - 1];
            }
            return counters;
        }

    }

    static class ModifySolutionAfterViewAnswer {

        public int[] corpFlightBookings(int[][] bookings, int n) {
            int[] result = new int[n];
            for (int[] booking : bookings) {
                result[booking[0] - 1] += booking[2];
                if (booking[1] != n) {
                    result[booking[1]] -= booking[2];
                }
            }
            for (int i = 1; i < n; i++) {
                result[i] += result[i - 1];
            }
            return result;
        }

    }

    public static void main(String[] args) {
        AloneSolution bookings = new AloneSolution();
        bookings.corpFlightBookings(null, 2);
    }

}
