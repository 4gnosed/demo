package com.example.demo.algorithm;

import java.util.*;

public class LeetCode78 {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0,nums);
        return ans;
    }

    private void dfs(int cur, int[] nums){
        if(cur == nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }
        path.add(nums[cur]);
        dfs(cur+1,nums);
        path.remove(path.size()-1);
        dfs(cur+1,nums);
    }
}
