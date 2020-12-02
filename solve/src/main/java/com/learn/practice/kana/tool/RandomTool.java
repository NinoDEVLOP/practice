package com.learn.practice.kana.tool;

/**
 * @author qingchuan.xia
 * @date 2019/8/29 20:47
 */
public class RandomTool {

    /**
     * 随机掷出给定最大值从0~max的整数
     */
    public static int random(int max) {
        return (int)(Math.random()*(max+1));
    }

    public static void main(String[] args) {
//        for (int i = 0 ;i < 20; i++) {
//            System.out.println(RandomTool.random(6));
//        }
        String range = ",3,4,";
        System.out.println(range.substring(1,range.length() -1));
    }

}
