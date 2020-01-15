package com.test.study.model.question;

/**
 * @author qingchuan.xia
 * @date 2019/8/31 20:37
 */
public interface Question {

    String getQuestion();

    String getSolution();

    boolean isCorrect(String answer);

}
