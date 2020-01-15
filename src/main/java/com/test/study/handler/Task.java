package com.test.study.handler;

/**
 * @author qingchuan.xia
 * @date 2019/8/30 10:54
 */
public interface Task<T> {

    /**
     * 暂时还没有确定入参，所以使用不定入参
     * @return 执行结果
     */
    T doInternal(Object ... params);

}
