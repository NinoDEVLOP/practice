package com.learn.practice.leetcode;

public class VersionCompare {

    public static class My_Solution {

        public int compareVersion(String version1, String version2) {
            String[] verArr1 = version1.split("\\.");
            String[] verArr2 = version2.split("\\.");
            int maxLength = Math.max(verArr1.length, verArr2.length);
            int result = 0;
            for (int i = 0; i < maxLength; i++) {
                int ver1 = getArrValue(i, verArr1);
                int ver2 = getArrValue(i, verArr2);
                if (ver1 == ver2) {
                    continue;
                }
                result = ver1 > ver2 ? 1 : -1;
                break;
            }
            return result;
        }

        public int getArrValue(int index, String[] verArr) {
            return Integer.parseInt(index < verArr.length ? verArr[index] : "0");
        }

    }

    public static void main(String[] args) {
        My_Solution solution = new My_Solution();
        int i = solution.compareVersion("7.2.3.4", "7.2.4");
        System.out.println(i);
    }


}
