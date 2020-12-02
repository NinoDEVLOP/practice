package com.learn.practice.kana.model.kana;

/**
 * 假名
 *
 * @author qingchuan.xia
 * @date 2019/8/29 20:34
 */
public class Kana {

    /**
     * 平假名
     */
    private String hiragana;

    /**
     * 片假名
     */
    private String katakana;

    /**
     * 发音
     */
    private String pronunciation;

    public Kana(String hiragana,String katakana,String pronunciation) {
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.pronunciation = pronunciation;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getKatakana() {
        return katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    @Override
    public String toString() {
        return "Kana{" +
            "hiragana(平假名)='" + hiragana + '\'' +
            ", katakana（片假名）='" + katakana + '\'' +
            ", pronunciation='" + pronunciation + '\'' +
            '}';
    }
}
