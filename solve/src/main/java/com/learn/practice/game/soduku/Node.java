package com.learn.practice.game.soduku;

/**
 * 数独节点信息
 *
 * @author qingchuan.xia
 * @date 2019/10/21 10:00
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
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

    public Node() {}

    public Node(Integer x, String y, Integer z, Integer value) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.value = value;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

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
