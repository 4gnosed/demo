package com.example.demo.algorithm;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        int completeCourse = 0;

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] s : prerequisites) {
            graph.get(s[0]).add(s[1]);
            inDegree[s[1]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (queue.size() > 0) {
            int course = queue.poll();
            completeCourse++;

            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return completeCourse == numCourses;
    }
}
