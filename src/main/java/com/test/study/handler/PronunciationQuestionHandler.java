package com.test.study.handler;

import com.test.study.model.kana.Kana;
import com.test.study.tool.ConsoleTool;
import com.test.study.tool.RandomTool;

/**
 * @author qingchuan.xia
 * @date 2019/8/30 17:38
 */
public class PronunciationQuestionHandler implements Task {

    @Override
    public Object doInternal(Object... params) {
        Kana kana = (Kana) params[0];
        int type = RandomTool.random(2) % 2;
        String question = type == 0 ? kana.getHiragana() : kana.getKatakana();
        System.out.println(question + " 怎么读?请输入读音罗马字(回车键提交答案)");
        String answer = ConsoleTool.receive();
        return null;
    }
}
