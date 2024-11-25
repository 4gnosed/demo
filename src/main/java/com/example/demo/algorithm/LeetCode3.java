package com.example.demo.algorithm;

import java.util.*;

class LeetCode3 {
    public int lengthOfLongestSubstring(String s) {
        HashSet<String> set = new HashSet<>();
        int left=0,ans=0;
        for(int right=0;right<s.length();right++){
            String c = s.charAt(right)+"";
            if(set.contains(c)){
                while(set.contains(c)){
                    set.remove(s.charAt(left)+"");
                    left++;
                }
            }else{
                ans = Math.max(ans,right-left+1);
            }
            set.add(c);
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode3 solution = new LeetCode3();
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
}