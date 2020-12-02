package com.learn.practice.kana.model.question;

import com.learn.practice.kana.model.kana.Kana;

/**
 * @author qingchuan.xia
 * @date 2019/8/31 21:06
 */
public class KatakanaQuestion extends KanaQuestionAdapter {
    public KatakanaQuestion(Kana kana) {
        super(kana);
    }

    @Override
    public String getQuestion() {
        return "请回答片假名，发音为:" + kana.getPronunciation();
    }

    @Override
    public String getSolution() {
        return kana.getKatakana();
    }

    @Override
    public boolean isCorrect(String answer) {
        return kana.getKatakana().equals(answer);
    }
}
