package com.test.study.factory;

import com.test.study.model.kana.Kana;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author qingchuan.xia
 * @date 2019/8/29 20:53
 */
@SuppressWarnings("unchecked")
public class DefaultKanaFactory implements KanaFactory {

    private List<Object> rowOfKana;

    public DefaultKanaFactory() {
        int row = 10;
        this.rowOfKana = new ArrayList<>(row);

        //元音行
        List<Kana> Kana = new ArrayList<>(5);
        Kana.add(new Kana("あ", "ア", "a"));
        Kana.add(new Kana("い", "イ", "i"));
        Kana.add(new Kana("う", "ウ", "u"));
        Kana.add(new Kana("え", "エ", "e"));
        Kana.add(new Kana("お", "オ", "o"));
        rowOfKana.add(Kana);

        //か行
        List<Kana> kKana = new ArrayList<>(5);
        kKana.add(new Kana("か", "カ", "ka"));
        kKana.add(new Kana("き", "キ", "ki"));
        kKana.add(new Kana("く", "ク", "ku"));
        kKana.add(new Kana("け", "ケ", "ke"));
        kKana.add(new Kana("こ", "コ", "ko"));
        rowOfKana.add(kKana);

        //さ
        List<Kana> saKana = new ArrayList<>(5);
        saKana.add(new Kana("さ", "サ", "sa"));
        saKana.add(new Kana("し", "シ", "si"));
        saKana.add(new Kana("す", "ス", "su"));
        saKana.add(new Kana("せ", "セ", "se"));
        saKana.add(new Kana("そ", "ソ", "so"));
        rowOfKana.add(saKana);

        //た
        List<Kana> tKana = new ArrayList<>(5);
        tKana.add(new Kana("た", "タ", "ta"));
        tKana.add(new Kana("ち", "チ", "chi"));
        tKana.add(new Kana("つ", "ツ", "tsu"));
        tKana.add(new Kana("て", "テ", "te"));
        tKana.add(new Kana("と", "ト", "to"));
        rowOfKana.add(tKana);

        //な
        List<Kana> nKana = new ArrayList<>(5);
        nKana.add(new Kana("な", "ナ", "na"));
        nKana.add(new Kana("に", "ニ", "ni"));
        nKana.add(new Kana("ぬ", "ヌ", "nu"));
        nKana.add(new Kana("ね", "ネ", "ne"));
        nKana.add(new Kana("の", "ノ", "no"));
        rowOfKana.add(nKana);

        //は
        List<Kana> hKana = new ArrayList<>(5);
        hKana.add(new Kana("は", "ハ", "ha"));
        hKana.add(new Kana("ひ", "ヒ", "hi"));
        hKana.add(new Kana("ふ", "フ", "hu"));
        hKana.add(new Kana("へ", "ヘ", "he"));
        hKana.add(new Kana("ほ", "ホ", "ho"));
        rowOfKana.add(hKana);

        //ま
        List<Kana> mKana = new ArrayList<>(5);
        mKana.add(new Kana("ま", "マ", "ma"));
        mKana.add(new Kana("み", "ミ", "mi"));
        mKana.add(new Kana("む", "ム", "mu"));
        mKana.add(new Kana("め", "メ", "me"));
        mKana.add(new Kana("も", "モ", "mo"));
        rowOfKana.add(mKana);

        //や
        List<Kana> yKana = new ArrayList<>(5);
        yKana.add(new Kana("や", "ヤ", "ya"));
        yKana.add(null);
        yKana.add(new Kana("ゆ", "ユ", "yu"));
        yKana.add(null);
        yKana.add(new Kana("よ", "ヨ", "yo"));
        rowOfKana.add(yKana);

        //ら
        List<Kana> rKana = new ArrayList<>(5);
        rKana.add(new Kana("ら", "ラ", "ra"));
        rKana.add(new Kana("り", "リ", "ri"));
        rKana.add(new Kana("る", "ル", "ru"));
        rKana.add(new Kana("れ", "レ", "re"));
        rKana.add(new Kana("ろ", "ロ", "ro"));
        rowOfKana.add(rKana);

        //わ
        List<Kana> wKana = new ArrayList<>(5);
        wKana.add(new Kana("わ", "ワ", "a"));
        wKana.add(null);
        wKana.add(null);
        wKana.add(null);
        wKana.add(new Kana("を", "ヲ", "o"));
        rowOfKana.add(wKana);

        //ん
        List<Kana> aloneKana = new ArrayList<>();
        aloneKana.add(new Kana("ん", "ン", "n"));
        aloneKana.add(null);
        aloneKana.add(null);
        aloneKana.add(null);
        aloneKana.add(null);
        rowOfKana.add(aloneKana);
    }

    @Override
    public List<Kana> createKana() {
        List<Kana> allKana = new ArrayList<>();
        for (Object list : rowOfKana) {
            allKana.addAll((List<Kana>) list);
        }
        this.removeNull(allKana);
        return allKana;
    }

    @Override
    public List<Kana> createByRow(int rowIndex) {
        List<Kana> kanas = new ArrayList<>((List<Kana>) rowOfKana.get(rowIndex));
        this.removeNull(kanas);
        return kanas;
    }

    @Override
    public List<Kana> createByColumn(int columnIndex) {
        List<Kana> columnOfKana = new ArrayList<>();
        for (Object list : rowOfKana) {
            Kana kana = ((List<Kana>) list).get(columnIndex);
            columnOfKana.add(kana);
        }
        this.removeNull(columnOfKana);
        return columnOfKana;
    }

    @Override
    public List<Kana> createByRow(int start, int end) {
        List<Kana> rowKanas = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            rowKanas.addAll((List<Kana>) rowOfKana.get(i));
        }
        this.removeNull(rowKanas);
        return rowKanas;
    }

    private void removeNull(List list) {
        list.removeAll(Collections.singleton(null));
    }

}
