package com.learn.practice.kana.model.question;

import com.learn.practice.kana.model.kana.Kana;

/**
 * @author qingchuan.xia
 * @date 2019/8/31 21:09
 */
public class KatakanaPronounceQuestion extends KanaQuestionAdapter {

    public KatakanaPronounceQuestion(Kana kana) {
        super(kana);
    }

    @Override
    public String getQuestion() {
        return "请回答发音罗马字:" + kana.getKatakana();
    }

    @Override
    public String getSolution() {
        return kana.getPronunciation();
    }

    @Override
    public boolean isCorrect(String answer) {
        return kana.getPronunciation().equals(answer);
    }
}
