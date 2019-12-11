package com.shark.example.circle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private final int nodeCount;
    private final List<List<Integer>> adj;

    public Graph(int nodeCount) {
        this.nodeCount = nodeCount;
        adj = new ArrayList<>(nodeCount);
        for (int i = 0; i < nodeCount; i++) {
            adj.add(new LinkedList<>());
        }
    }

    public boolean isCyclicUtil(int i, boolean[] visited, boolean[] recordStack) {
        System.out.println("i: " + i);
        System.out.println("visited: " + new Gson().toJson(visited));
        System.out.println("recordStack: " + new Gson().toJson(recordStack));
        // Mark the current node as visited and
        // part of recursion stack
        if (recordStack[i]) {
            return true;
        }
        if (visited[i]) {
            return false;
        }
        visited[i] = true;
        recordStack[i] = true;
        List<Integer> children = adj.get(i);

        for (Integer ren : children) {
            System.out.println("ren: " + ren);
            if (isCyclicUtil(ren, visited, recordStack)) {
                return true;
            }
        }
        recordStack[i] = false;

        return false;
    }

    public void addEdge(int source, int dest) {
        adj.get(source).add(dest);
    }

    public boolean isCyclic() {
        // Mark all the vertices as not visited and
        // not part of recursion stack
        boolean[] visited = new boolean[nodeCount];
        boolean[] recordStack = new boolean[nodeCount];
        // Call the recursive helper function to
        // detect cycle in different DFS trees
        for (int i = 0; i < nodeCount; i++) {
            if (isCyclicUtil(i, visited, recordStack)) {
                return true;
            }
        }
        return false;
    }
}
