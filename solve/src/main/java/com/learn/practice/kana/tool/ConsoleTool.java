package com.learn.practice.kana.tool;

import java.io.IOException;

/**
 * @author qingchuan.xia
 * @date 2019/8/30 16:46
 */
public class ConsoleTool {

    public static String receive() {
        try {
            byte[] b = new byte[40];
            int count = System.in.read(b);
            return new String(b, 0, count, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void simulatClear() {
        for (int i = 0; i < 30; i++) {
            System.out.println("\n\r");
        }
    }

}
