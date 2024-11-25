package com.example.demo.algorithm;
import java.util.*;
import java.util.concurrent.*;

public class LeetCode102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        LinkedBlockingQueue<TreeNode> q = new LinkedBlockingQueue<>();
        q.offer(root);
        while(q.size()>0){
            int levelSize = q.size();
            ArrayList<Integer> level = new ArrayList<>();
            while(levelSize-->0){
                TreeNode node = q.poll();
                level.add(node.val);
                if(node.left!=null){
                    q.offer(node.left);
                }
                if(node.right!=null){
                    q.offer(node.right);
                }
            }
            ans.add(level);
        }
        return ans;
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        LeetCode102 leetCode102 = new LeetCode102();
        TreeNode root = leetCode102.new TreeNode(3);
        TreeNode left = leetCode102.new TreeNode(9);
        TreeNode right = leetCode102.new TreeNode(20);
        TreeNode rightLeft = leetCode102.new TreeNode(15);
        TreeNode rightRight = leetCode102.new TreeNode(7);
        right.left = rightLeft;
        right.right = rightRight;
        root.left = left;
        root.right = right;
        System.out.println(leetCode102.levelOrder(root));
    }

}