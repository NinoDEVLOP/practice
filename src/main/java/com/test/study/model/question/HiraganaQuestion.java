package com.test.study.model.question;

import com.test.study.model.kana.Kana;

/**
 * @author qingchuan.xia
 * @date 2019/8/31 20:47
 */
public class HiraganaQuestion extends KanaQuestionAdapter {
    public HiraganaQuestion(Kana kana) {
        super(kana);
    }

    @Override
    public String getQuestion() {
        return "请回答平假名，发音为:" + kana.getPronunciation();
    }

    @Override
    public String getSolution() {
        return kana.getHiragana();
    }

    @Override
    public boolean isCorrect(String answer) {
        return kana.getHiragana().equals(answer);
    }
}
