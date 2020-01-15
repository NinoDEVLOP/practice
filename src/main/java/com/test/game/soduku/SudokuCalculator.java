package com.test.game.soduku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 遍历候选值，并从
 *
 * @author qingchuan.xia
 * @date 2019/10/21 14:54
 */
public class SudokuCalculator {

    /**
     * count,HashMap
     * Node,Set  count = Set.length
     * 记录还有候选值多少，分组保存相关坐标
     */
    private HashMap<Integer, Object> group = new HashMap<>();

    /**
     * 单维度候选值列表 x-，y-，z- = ArrayList
     */
    private HashMap<String, ArrayList<Integer>> lineOfCandidate = new HashMap<>(81);
    /**
     * 记录空点
     */
    private int blankNodeCount = 0;
    private String[] yArr = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    private ArrayList<Integer> valueArray;

    {
        valueArray = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    public void calc(NodeCacheRepo repo) {
        for (int x = 1; x < 10; x++) {
            for (String y : yArr) {
                int z = calcZSite(x, y);
                if (repo.contains(x, y, z)) {
                    continue;
                }

                ArrayList<Integer> pointCandidate = new ArrayList<>(valueArray);

                Collection<Node> yLineNode = repo.getVNodes(y);
                Collection<Node> xStingNode = repo.getHNodes(x);
                Collection<Node> zBoxNode = repo.getZNodes(z);

                filterCandidate(pointCandidate, yLineNode);//过滤横行所有候选
                filterCandidate(pointCandidate, xStingNode);//过滤竖列所有候选
                filterCandidate(pointCandidate, zBoxNode);//过滤方格所有候选

                String xKey = "x-" + x;
                String yKey = "y-" + y;
                String zKey = "z-" + z;
                ArrayList<Integer> xCandidates = lineOfCandidate.get(xKey);
                ArrayList<Integer> yCandidates = lineOfCandidate.get(yKey);
                ArrayList<Integer> zCandidates = lineOfCandidate.get(zKey);
                if (xCandidates == null) {
                    xCandidates = initLineOfCandidate(xKey, xStingNode);
                }
                if (yCandidates == null) {
                    yCandidates = initLineOfCandidate(yKey, yLineNode);
                }

                if (zCandidates == null) {
                    zCandidates = initLineOfCandidate(zKey, zBoxNode);
                }
                Node node = new Node(x, y, z, null);
                if (pointCandidate.size() == 1) {
                    Integer value = pointCandidate.get(0);
                    node.setValue(value);
                    repo.put(x, y, z, value);
                    pointCandidate.clear();
                    xCandidates.remove(value);
                    yCandidates.remove(value);
                    zCandidates.remove(value);
                } else {
                    blankNodeCount++;
                }
                ArrayList<Node> set = (ArrayList<Node>) group.get(pointCandidate.size());
                if (set == null) {
                    set = new ArrayList<>();
                }
                set.add(node);
                group.put(pointCandidate.size(), set);
            }
        }

        while (blankNodeCount != 0) {
            for (int i = 1; i < 10; i++) {
                ArrayList<Node> list = (ArrayList<Node>) group.get(i);
                if (list == null) {
                    continue;
                }
                Iterator<Node> iterator = list.iterator();
                while (iterator.hasNext()) {
                    Node node = iterator.next();

                    //剩余点候选值从单维候选集合中取交集
                    ArrayList<Integer> xCandidates = lineOfCandidate.get("x-" + node.getX());
                    ArrayList<Integer> yCandidates = lineOfCandidate.get("y-" + node.getY());
                    ArrayList<Integer> zCandidates = lineOfCandidate.get("z-" + node.getZ());

                    ArrayList<Integer> siteCandidate = new ArrayList<>(xCandidates);
                    retainCandidate(siteCandidate, yCandidates);
                    retainCandidate(siteCandidate, zCandidates);

                    boolean againFlag = true;
                    if (siteCandidate.size() > 0 && siteCandidate.size() < 3) {
                        Integer value = siteCandidate.get(0);
                        repo.put(node.getX(), node.getY(), node.getZ(), value);

                        xCandidates.remove(value);
                        yCandidates.remove(value);
                        zCandidates.remove(value);
                        blankNodeCount--;
                        iterator.remove();
                        againFlag = false;
                    }

                    if (againFlag && siteCandidate.size() != i) {
                        iterator.remove();
                        ArrayList<Node> upLvlist = (ArrayList<Node>)group.get(siteCandidate.size());
                        if (upLvlist == null) {
                            upLvlist = new ArrayList<>();
                            group.put(i, upLvlist);
                        }
                        upLvlist.add(node);
                    }
                }
            }
        }

    }

    /**
     * 初始化单维候选值
     */
    private ArrayList<Integer> initLineOfCandidate(String key, Collection<Node> existNode) {
        ArrayList<Integer> lineCandidate = new ArrayList<>(valueArray);
        filterCandidate(lineCandidate, existNode);
        lineOfCandidate.put(key, lineCandidate);
        return lineCandidate;
    }

    /**
     * 筛选结果集
     */
    private void filterCandidate(List<Integer> candidate, Collection<Node> existNode) {
        for (Node node : existNode) {
            candidate.remove(node.getValue());
        }
    }

    /**
     * 取两个参数集合的交集，并且最终结果更新到result参数中
     *
     * @param result
     * @param source
     */
    private void retainCandidate(ArrayList<Integer> result, ArrayList<Integer> source) {
        result.retainAll(source);
    }

    /**
     * 根据X.Y坐标计算z轴
     */
    private static Integer calcZSite(Integer x, String y) {
        int h = (x - 1) / 3 + 1;//1-3
        int index = "ABCDEFGHI".indexOf(y);
        int m = (index / 3) * 3; //0-2 * 3 = 0 3 6
        Integer z = h + m;// X = h - m => Z = h - m
        return z;
    }


}
