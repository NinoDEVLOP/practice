package com.learn.practice.kana.manager;

import com.learn.practice.kana.factory.DefaultKanaFactory;
import com.learn.practice.kana.factory.KanaFactory;
import com.learn.practice.kana.handler.KanaQuestionConverter;
import com.learn.practice.kana.handler.Task;
import com.learn.practice.kana.model.kana.Kana;
import com.learn.practice.kana.model.question.HiraganaPronounceQuestion;
import com.learn.practice.kana.model.question.HiraganaQuestion;
import com.learn.practice.kana.model.question.KatakanaPronounceQuestion;
import com.learn.practice.kana.model.question.KatakanaQuestion;
import com.learn.practice.kana.model.question.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qingchuan.xia
 * @date 2019/9/3 15:45
 */
public class DefaultSourceManager implements SourceManager {

    private KanaFactory kanaSource;
    private Task<Question> kanaQuestionConverter;
    private List<Class> questionType;

    public DefaultSourceManager() {
        this.kanaSource = new DefaultKanaFactory();
        this.kanaQuestionConverter = new KanaQuestionConverter();
        this.questionType = new ArrayList<>();
        this.questionType.add(HiraganaPronounceQuestion.class);
        this.questionType.add(HiraganaQuestion.class);
        this.questionType.add(KatakanaPronounceQuestion.class);
        this.questionType.add(KatakanaQuestion.class);
    }

    @Override
    public List<Question> getQuestionByRange(String range, String strategy) {
        String[] rangeParam = range.substring(1, range.length() - 1).split(",");
        List<Kana> kanas;
        if (rangeParam.length >= 2) {
            kanas = this.kanaSource.createByRow(Integer.valueOf(rangeParam[0]), Integer.valueOf(rangeParam[1]));
        } else {
            kanas = this.kanaSource.createByRow(Integer.valueOf(rangeParam[0]));
        }
        for (Kana kana :
            kanas) {
            this.kanaQuestionConverter.doInternal();
        }


        return null;
    }

    @Override
    public String record(Map<String, Question> qna) {
        return null;
    }

    private List<Class> getQuestionTypeByStrategy(String strategy) {
        List<Class> finalQuestionType = new ArrayList<>();
        int repeatCount = repeatCount(strategy);
        while(repeatCount > 0) {
            finalQuestionType.addAll(this.questionType);
            repeatCount -= 1;
        }
        return finalQuestionType;
    }

    private int repeatCount(String strategy){
        return 1;
    }

    private boolean isRandomSort(String strategy){
        return false;
    }
}
