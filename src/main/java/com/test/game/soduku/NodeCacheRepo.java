package com.test.game.soduku;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author qingchuan.xia
 * @date 2019/10/21 10:04
 */
public class NodeCacheRepo {

    private HashMap<Integer, Set<Node>> hNodeMap;

    private HashMap<String, Set<Node>> vNodeMap;

    private HashMap<Integer, Set<Node>> zNodeMap;

    private HashMap<String, Node> nodeMap;

    public static Comparator<Node> comparable;

    static {
        comparable = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };
    }

    public NodeCacheRepo() {
        nodeMap = new HashMap<>(100);
        hNodeMap = new HashMap<>(15);
        vNodeMap = new HashMap<>(15);
        zNodeMap = new HashMap<>(15);

    }

    public boolean put(Integer x, String y, Integer z, Integer value) {
        Node newValueNode = new Node(x, y, z, value);
        if (!isValid(newValueNode)) {
            return false;
        }
        if (modify(newValueNode)) {
            return true;
        }

        Set<Node> xNodes = hNodeMap.get(x);
        if (xNodes == null) {
            xNodes = new TreeSet<>(comparable);
            hNodeMap.put(newValueNode.getX(), xNodes);
        }
        xNodes.add(newValueNode);

        Set<Node> yNodes = vNodeMap.get(y);
        if (yNodes == null) {
            yNodes = new TreeSet<>(comparable);
            vNodeMap.put(newValueNode.getY(), yNodes);
        }
        yNodes.add(newValueNode);

        Set<Node> zNodes = zNodeMap.get(z);
        if (zNodes == null) {
            zNodes = new TreeSet<>(comparable);
            zNodeMap.put(newValueNode.getZ(), zNodes);
        }
        zNodes.add(newValueNode);

        String key = "" + x + y + z;
        nodeMap.put(key, newValueNode);

        return true;
    }

    public boolean modify(Integer x, String y, Integer z, Integer value) {
        Node newValueNode = new Node(x, y, z, value);
        if (!isValid(newValueNode)) {
            return false;
        }
        return modify(newValueNode);
    }

    public boolean contains(Integer x, String y, Integer z) {
        return nodeMap.containsKey(x + y + z);
    }

    private boolean modify(Node node) {
        String key = "" + node.getX() + node.getY() + node.getZ();
        if (!nodeMap.containsKey(key)) {
            return false;
        }
        Node curNode = nodeMap.get(key);
        curNode.setValue(node.getValue());
        return true;
    }

    public Collection<Node> getHNodes(Integer x) {
        Collection<Node> nodes = hNodeMap.get(x);
        return nodes == null ? new HashSet<Node>() : new HashSet<Node>(nodes);
    }

    public Collection<Node> getVNodes(String y) {
        Collection<Node> nodes = vNodeMap.get(y);
        return nodes == null ? new HashSet<Node>() : new HashSet<Node>(nodes);
    }

    public Collection<Node> getZNodes(Integer z) {
        Collection<Node> nodes = zNodeMap.get(z);
        return nodes == null ? new HashSet<Node>() : new HashSet<Node>(nodes);
    }

    /**
     * 会忽略与坐标相同节点的值
     */
    public boolean isValid(Node node) {
        return isValueValid(node.getValue()) &&
            isValidForH(node) &&
            isValidForV(node) &&
            isValidForZ(node);
    }

    private boolean isValueValid(Integer value) {
        return value > 0 && value < 10;
    }

    private boolean isValidForH(Node node) {
        return node.getX() > 0 && node.getX() < 10 && noExistValueSkipSamePoint(getHNodes(node.getX()), node);
    }

    private boolean isValidForV(Node node) {
        return node.getY().length() == 1 && "ABCDEFGHI".contains(node.getY()) && noExistValueSkipSamePoint(getVNodes(node.getY()), node);
    }

    private boolean isValidForZ(Node node) {
        return node.getZ() > 0 && node.getZ() < 10 && noExistValueSkipSamePoint(getZNodes(node.getZ()), node);
    }

    private boolean noExistValueSkipSamePoint(Collection<Node> nodes, Node node) {
        for (Node existNode : nodes) {
            if (existNode.equals(node)) {
                continue;
            }
            if (existNode.getValue().equals(node.getValue())) {
                return false;
            }
        }
        return true;
    }

}
