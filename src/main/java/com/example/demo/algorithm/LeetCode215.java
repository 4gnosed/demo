package com.example.demo.algorithm;


import java.util.*;

public class LeetCode215 {
    public int findKthLargest(int[] nums, int k) {
//        int n = nums.length;
//        PriorityQueue<Integer> q = new PriorityQueue<>(n,Comparator.reverseOrder());
//        for(int i: nums){
//            q.offer(i);
//        }
//        for(int i=0;i<k-1;i++){
//            q.poll();
//        }
        //优化
        PriorityQueue<Integer> q = new PriorityQueue<>(k, Comparator.naturalOrder());
        for (int num : nums) {
            if (q.size() < k) {
                q.offer(num);
            } else if (q.peek() < num) {
                q.poll();
                q.offer(num);
            }
        }
        return q.peek();
    }

public static void main(String[] args) {
        LeetCode215 leetCode215 = new LeetCode215();
        int[] nums = {3,2,1,5,6,4};
        System.out.println(leetCode215.findKthLargest(nums, 2));
    }

}
