package com.test.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author xiaqingchuan
 * @version 1.0
 * @date 2020/01/2020/1/14 15:07
 */
public class DeleteNodes {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> resultNode = new ArrayList<>();
        resultNode.add(root);
        delNodes(root, to_delete, resultNode);
        resultNode.removeIf(Objects::isNull);
        return resultNode;
    }

    private void delNodes(TreeNode root, int[] to_delete, List<TreeNode> tempNode) {
        if (root == null) {
            return;
        }
        delNodes(root.left, to_delete, tempNode);
        delNodes(root.right, to_delete, tempNode);

        if (isDeleteValue(root.val, to_delete)) {
            if (root.left != null) {
                if (isDeleteValue(root.left.val, to_delete)) {
                    root.left = null;
                } else {
                    tempNode.add(root.left);
                }
            }
            if (root.right != null) {
                if (isDeleteValue(root.right.val, to_delete)) {
                    root.right = null;
                } else {
                    tempNode.add(root.right);
                }
            }
            root.left = null;
            root.right = null;
        }
    }

    private boolean isDeleteValue(int val, int[] deleted) {
        for (int del : deleted) {
            if (val == del) {
                return true;
            }
        }
        return false;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(3);
        DeleteNodes deleteNodes = new DeleteNodes();
        System.out.println(deleteNodes.delNodes(root, new int[]{3, 5}));
    }

}
