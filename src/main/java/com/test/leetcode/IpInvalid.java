package com.test.leetcode;

import java.util.LinkedList;

/**
 * @author xiaqingchuan
 * @version 1.0
 * @date 2020/01/2020/1/13 10:34
 */
public class IpInvalid {

    public String defangIpAddr(String address) {
        char[] charArr = address.toCharArray();
        char[] charDefangIpArr = new char[address.length() + 6];
        for (int i = 0, j = 0; i < charArr.length; i++) {
            if (charArr[i] != '.') {
                charDefangIpArr[i + j] = charArr[i];
                continue;
            }
            charDefangIpArr[i + j] = '[';
            charDefangIpArr[i + j + 1] = '.';
            charDefangIpArr[i + j + 2] = ']';
            j += 2;
        }
        return String.valueOf(charDefangIpArr);
    }

    public static void main(String[] args) {
        String a = "255.255.255.255";
        IpInvalid ipInvalid = new IpInvalid();
        System.out.println(ipInvalid.defangIpAddr(a));
    }


}
