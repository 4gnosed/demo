package com.example.demo.algorithm;

class LeetCode34 {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if(n == 0){
            return new int[]{-1,-1};
        }
        int f = findFirst(nums, target);
        if(f>=n || nums[f]!=target){
            return new int[]{-1,-1};
        }
        int l = findLast(nums, target);
        return new int[]{f,l};
    }

    private static int findFirst(int[] nums, int target){
        int left = 0, right = nums.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(target<=nums[mid]){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    private static int findLast(int[] nums, int target){
        int left = 0, right = nums.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(target>=nums[mid]){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return right;
    }
}