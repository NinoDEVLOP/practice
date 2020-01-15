package com.test.study.model.question;

import com.test.study.model.kana.Kana;

/**
 * @author qingchuan.xia
 * @date 2019/8/31 20:40
 */
public class KanaQuestionAdapter implements Question {

    /**
     * 将被转换为问题的假名类
     */
    protected Kana kana;

    public KanaQuestionAdapter(Kana kana){
        this.kana = kana;
    }

    @Override
    public String getQuestion() {
        return "";
    }

    @Override
    public String getSolution() {
        return "";
    }

    @Override
    public boolean isCorrect(String answer) {
        return false;
    }
}
