package com.practice.demo;

import java.util.TreeMap;

public class TreeMapTests {

    public static void main(String[] args) {
        TreeMap<String,String> treeMap = new TreeMap<>();
        String[] strArr = new String[] {"1","aaa","abac","test","position","time","cache","nonce"};

        for (String str : strArr) {
            treeMap.put(str, "");
        }
        System.out.println(treeMap.keySet().toString());
    }

}
