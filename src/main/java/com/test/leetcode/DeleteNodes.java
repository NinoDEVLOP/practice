package com.test.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaqingchuan
 * @version 1.0
 * @date 2020/01/2020/1/14 15:07
 */
public class DeleteNodes {

    static class TryFirstAnswerSolution {
        private int[] delDict = new int[1001];
        private List<TreeNode> resultList = new ArrayList<>();

        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            for (int delVal : to_delete) {
                delDict[delVal] = 1;
            }
            if (!isDeleteValue(root.val)) {
                resultList.add(root);
            }
            delNodes(root);
            return resultList;
        }

        private TreeNode delNodes(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode left = root.left;
            TreeNode right = root.right;
            if (isDeleteValue(root.val)) {
                if (left != null && !isDeleteValue(left.val)) {
                    resultList.add(root.left);
                }
                if (right != null && !isDeleteValue(right.val)) {
                    resultList.add(root.right);
                }
            }

            root.left = delNodes(left);
            root.right = delNodes(right);
            return isDeleteValue(root.val) ? null : root;
        }

        private boolean isDeleteValue(int val) {
            return delDict[val] == 1;
        }
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

    static class MySolution {

        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            List<TreeNode> resultNode = new ArrayList<>();
            resultNode.add(root);
            delNodes(root, to_delete, resultNode);
            //resultNode.removeIf(Objects::isNull);
            return resultNode;
        }

        private void delNodes(TreeNode root, int[] to_delete, List<TreeNode> tempNode) {
            if (root == null) {
                return;
            }
            delNodes(root.left, to_delete, tempNode);
            delNodes(root.right, to_delete, tempNode);

            if (root.left != null && isDeleteValue(root.left.val, to_delete)) {
                tempNode.remove(root.left);
                root.left = null;
            }
            if (root.right != null && isDeleteValue(root.right.val, to_delete)) {
                tempNode.remove(root.left);
                root.right = null;
            }
            if (isDeleteValue(root.val, to_delete)) {
                tempNode.remove(root);
                if (root.left != null) {
                    tempNode.add(root.left);
                }
                if (root.right != null) {
                    tempNode.add(root.right);
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
    }

    static class FirstAnswerSolution {
        List<TreeNode> list = new ArrayList<>();
        int[] dict = new int[1001];

        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            if (root == null) {
                return list;
            }
            for (int i : to_delete) {
                dict[i] = 1;
            }

            if (dict[root.val] == 0) {
                list.add(root);
            }
            delete(root);
            return list;
        }

        private TreeNode delete(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode right = root.right;
            TreeNode left = root.left;
            if (dict[root.val] == 1) {
                if (right != null && dict[right.val] == 0) {
                    list.add(right);
                }
                if (left != null && dict[left.val] == 0) {
                    list.add(left);
                }
            }
            root.left = delete(left);
            root.right = delete(right);
            return dict[root.val] == 1 ? null : root;

        }
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
//        TryFirstAnswerSolution deleteNodes = new TryFirstAnswerSolution();
//        System.out.println(deleteNodes.delNodes(root, new int[]{3, 5}));

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(3);
        TryFirstAnswerSolution deleteNodes = new TryFirstAnswerSolution();
        System.out.println(deleteNodes.delNodes(root, new int[]{2, 3}));

//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.right.right = new TreeNode(4);
//        TryFirstAnswerSolution deleteNodes = new TryFirstAnswerSolution();
//        System.out.println(deleteNodes.delNodes(root, new int[]{2, 1}));
    }

}
