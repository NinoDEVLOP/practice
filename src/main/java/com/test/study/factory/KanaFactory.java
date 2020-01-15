package com.test.study.factory;

import com.test.study.model.kana.Kana;

import java.util.List;

/**
 * @author qingchuan.xia
 * @date 2019/8/29 20:39
 */
public interface KanaFactory {

    List<Kana> createKana();

    /**
     * 根据五十音表创建制定行的所有假名
     * @param rowIndex
     * @return
     */
    List<Kana> createByRow(int rowIndex);

    /**
     * 根据五十音表创建制定段的所有假名
     * @param columnIndex
     * @return
     */
    List<Kana> createByColumn(int columnIndex);

    /**
     * 根据五十音表创建制定行的所有假名
     */
    List<Kana> createByRow(int start, int end);

}
