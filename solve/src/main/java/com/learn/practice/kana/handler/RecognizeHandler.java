package com.learn.practice.kana.handler;

import com.learn.practice.kana.model.kana.Kana;

/**
 * @author qingchuan.xia
 * @date 2019/8/30 10:04
 */
public class RecognizeHandler implements Task<String> {

    @Override
    public String doInternal(Object... params) {
        Kana kana = (Kana) params[0];
        StringBuilder builder = new StringBuilder();
        builder.append("发音:").append(" ").append(kana.getPronunciation()).append("\n");
        builder.append("平假名:").append(" ").append(kana.getHiragana()).append("\n");
        builder.append("片假名:").append(" ").append(kana.getKatakana()).append("\n");
        return builder.toString();
    }
}