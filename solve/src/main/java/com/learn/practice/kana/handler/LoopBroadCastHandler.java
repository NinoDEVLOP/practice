//package com.test.study.handler;
//
//import com.test.study.model.kana.Kana;
//import com.test.study.tool.ConsoleTool;
//import com.test.study.tool.RandomTool;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author qingchuan.xia
// * @date 2019/8/30 16:43
// */
//public class LoopBroadCastHandler implements Task {
//
//
//    private Object node = new Object();
//
//    @Override
//    public Object doInternal(Object... params) {
//        List<Kana> kanas = (List<Kana>) params[0];
//        Map<String, Object> cacheRandom = new HashMap<>(kanas.size());
//        int minLoop = kanas.size();
//        for (int i = 0; i < minLoop; i++) {
//            int index = RandomTool.random(kanas.size() - 1);
//            if (cacheRandom.put(index + "", node) != null) {
//                i -= 1;
//                continue;
//            }
//            Kana kana = kanas.get(index);
//            Object resultObj = nextHandler.doInternal(i, kana);
//            if (resultObj instanceof Boolean && !((Boolean) resultObj)) {
//                cacheRandom.remove(index + "");
//                minLoop += 1;
//            }
//            ConsoleTool.simulatClear();
//        }
//        return null;
//    }
//}
