package com.learn.practice.leetcode;

/**
 * @author xiaqingchuan
 * @version 1.0
 * @date 2020/01/2020/1/13 20:29
 */
public class MinFlips {

    public int minFlips(int a, int b, int c) {
        String aStr = Integer.toBinaryString(a);
        String bStr = Integer.toBinaryString(b);
        String cStr = Integer.toBinaryString(c);

        int index = 0;
        int count = 0;
        do {
            char cBitChar = cStr.length() > index ? cStr.charAt(cStr.length() - 1 - index) : '0';
            char bBitChar = bStr.length() > index ? bStr.charAt(bStr.length() - 1 - index) : '0';
            char aBitChar = aStr.length() > index ? aStr.charAt(aStr.length() - 1 - index) : '0';
            if (cBitChar == '0' && aBitChar == '1' && bBitChar == '1') {
                count += 2;
            } else if (cBitChar != (aBitChar | bBitChar)) {
                count += 1;
            }
            index++;

        } while (index < Math.max(aStr.length(), Math.max(cStr.length(), bStr.length())));
        return count;
    }

    public static void main(String[] args) {
        MinFlips minFlips = new MinFlips();
        minFlips.minFlips(2, 6, 6);
    }

}
