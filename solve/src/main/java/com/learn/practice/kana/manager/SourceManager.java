package com.learn.practice.kana.manager;

import com.learn.practice.kana.model.question.Question;

import java.util.List;
import java.util.Map;

/**
 * @author qingchuan.xia
 * @date 2019/8/31 21:17
 */
public interface SourceManager {

    /**
     * 根据范围和生成策略，生成问题集合
     */
    List<Question> getQuestionByRange(String range,String strategy);

    /**
     * 记录错题集
     */
    String record(Map<String,Question> qna);

}
