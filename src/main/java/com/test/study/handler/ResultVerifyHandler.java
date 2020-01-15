package com.test.study.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qingchuan.xia
 * @date 2019/8/30 10:05
 */
public class ResultVerifyHandler implements Task {

    private Map<String, Integer> errorRec = new HashMap<>();

    @Override
    public Object doInternal(Object... params) {
        String question = (String) params[0];
        String correct = (String) params[1];
        String answer = (String) params[2];
        if (correct.equals(answer)) {
            System.out.println("回答正确~");
            return true;
        } else {
            System.out.println("回答错误，正确答案：" + correct);
            int errorCount = !errorRec.containsKey(question) ? 1 : errorRec.get(question) + 1;
            errorRec.put(question, errorCount);
            return false;
        }
    }

    public void printErrorHistory() {
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
}