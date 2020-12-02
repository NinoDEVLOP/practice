package com.learn.practice.kana;

import com.learn.practice.kana.factory.DefaultKanaFactory;
import com.learn.practice.kana.factory.KanaFactory;
import com.learn.practice.kana.model.kana.Kana;
import com.learn.practice.kana.tool.RandomTool;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author qingchuan.xia
 * @date 2019/8/29 20:29
 */
public class Simulator {

    private static Object node = new Object();

    public static void main(String[] args) {

        KanaFactory factory = new DefaultKanaFactory();
        List<Kana> kanas = factory.createByRow(3, 3);
        practiceKana(kanas);
    }

    public static void recognize(List<Kana> kanas) {
        Map<String, Object> cacheRandom = new HashMap<>(kanas.size());
        for (int i = 0; i < kanas.size(); i++) {
            int index = RandomTool.random(kanas.size() - 1);
            if (cacheRandom.put(index + "", node) != null) {
                i -= 1;
                continue;
            }
            Kana kana = kanas.get(index);
            System.out.println(i);
            System.out.println(kana.getPronunciation());
            System.out.println(kana.getHiragana());
            System.out.println(kana.getKatakana());
            System.out.println("按回车键继续~");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Simulator.simulatClear();
        }
    }

    public static void practicePronunciation(List<Kana> kanas) {
        Map<String, Object> cacheRandom = new HashMap<>(kanas.size());
        Map<String, Integer> errorRec = new HashMap<>();
        int min = kanas.size();
        for (int i = 0; i < min; i++) {
            int index = RandomTool.random(kanas.size() - 1);
            if (cacheRandom.put(index + "", node) != null) {
                i -= 1;
                continue;
            }
            Kana kana = kanas.get(index);
            System.out.println(i);
            int type = RandomTool.random(2) % 2;
            String question = type == 0 ? kana.getHiragana() : kana.getKatakana();
            System.out.println(question);

            System.out.println("请输入读音罗马字");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next();
            if (kana.getPronunciation().equals(answer)) {
                System.out.println("回答正确");
            } else {
                System.out.println("回答错误，正确答案:" + kana.getPronunciation());
                cacheRandom.remove(index + "");
                int errorCount = !errorRec.containsKey(question) ? 1 : errorRec.get(question) + 1;
                errorRec.put(question, errorCount);
                min += 1;
            }
            System.out.println("按回车键继续答题~");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Simulator.simulatClear();
        }
        if (errorRec.isEmpty()) {
            return;
        }
        System.out.println("错题集中");
        StringBuilder questionBuilder = new StringBuilder();
        StringBuilder errorBuilder = new StringBuilder();
        for (String question : errorRec.keySet()) {
            questionBuilder.append(question).append("    ");
            errorBuilder.append(errorRec.get(question)).append("    ");
        }
        System.out.println(questionBuilder.toString());
        System.out.println(errorBuilder.toString());
    }

    public static void practiceKana(List<Kana> kanas) {
        Map<String, Object> cacheRandom = new HashMap<>(kanas.size());
        Map<String, Integer> errorRec = new HashMap<>();
        int min = kanas.size();
        for (int i = 0; i < min; i++) {
            int index = RandomTool.random(kanas.size() - 1);
            if (cacheRandom.put(index + "", node) != null) {
                i -= 1;
                continue;
            }
            Kana kana = kanas.get(index);
            System.out.println(i);
            System.out.println(kana.getPronunciation());
            int type = RandomTool.random(2) % 2;
            String correct = type == 0 ? kana.getHiragana() : kana.getKatakana();
            System.out.println("请输入" + (type == 0 ? "平假名" : "片假名"));

            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next();
            if (correct.equals(answer)) {
                System.out.println("回答正确");
            } else {
                System.out.println("回答错误，正确答案:" + correct);
                cacheRandom.remove(index + "");
                int errorCount = !errorRec.containsKey(correct) ? 1 : errorRec.get(correct) + 1;
                errorRec.put(correct, errorCount);
                min += 1;
            }
            System.out.println("按回车键继续答题~");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Simulator.simulatClear();
        }
        if (errorRec.isEmpty()) {
            return;
        }
        System.out.println("错题集中");
        StringBuilder questionBuilder = new StringBuilder();
        StringBuilder errorBuilder = new StringBuilder();
        for (String question : errorRec.keySet()) {
            questionBuilder.append(question).append("    ");
            errorBuilder.append(errorRec.get(question)).append("　    ");
        }
        System.out.println(questionBuilder.toString());
        System.out.println(errorBuilder.toString());
    }
}
