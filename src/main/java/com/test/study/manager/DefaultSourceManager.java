package com.test.study.manager;

import com.test.study.factory.DefaultKanaFactory;
import com.test.study.factory.KanaFactory;
import com.test.study.handler.KanaQuestionConverter;
import com.test.study.handler.Task;
import com.test.study.model.kana.Kana;
import com.test.study.model.question.HiraganaPronounceQuestion;
import com.test.study.model.question.HiraganaQuestion;
import com.test.study.model.question.KatakanaPronounceQuestion;
import com.test.study.model.question.KatakanaQuestion;
import com.test.study.model.question.Question;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
