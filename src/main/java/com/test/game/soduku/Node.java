package com.test.game.soduku;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数独节点信息
 *
 * @author qingchuan.xia
 * @date 2019/10/21 10:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Node {

    /**
     * 从左至右0-8
     */
    private Integer x;

    /**
     * 从上至下0-8
     */
    private String y;

    /**
     * 3 * 3格子，从左只有，从上至下顺序，0-8
     */
    private Integer z;

    /**
     * 该节点填写数值1-9
     */
    private Integer value;

    @Override
    public int hashCode() {
        return ("" + x + y + z + value).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }
}
