package com.example.demo.algorithm;

public class LeetCode167 {
    public int[] twoSum(int[] numbers, int target) {
        for(int i=0;i<numbers.length;i++){
            int left=i+1,right=numbers.length-1;
            while(left<=right){
                int mid = left + (right-left)/2;
                if(numbers[mid] == target-numbers[i]){
                    return new int[]{i+1,mid+1};
                }else if(numbers[mid] < target-numbers[i]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return new int[]{-1,-1};
    }
}