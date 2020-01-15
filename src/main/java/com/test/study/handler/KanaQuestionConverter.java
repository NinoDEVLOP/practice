package com.test.study.handler;

import com.test.study.model.kana.Kana;
import com.test.study.model.question.Question;

import java.lang.reflect.InvocationTargetException;

/**
 * 根据罗马字发音填入正确的假名
 *
 * @author qingchuan.xia
 * @date 2019/8/30 10:05
 */
public class KanaQuestionConverter implements Task<Question> {

    @Override
    public Question doInternal(Object... params) {
        Kana kana = (Kana) params[0];
        Class questionClass = (Class) params[1];
        try {
            return (Question) questionClass.getConstructor(Kana.class).newInstance(kana);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("questionClass is wrong type:" + questionClass.getName());
    }
}