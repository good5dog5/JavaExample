package com.shark.example.circle;

public class FindCircleExample {
    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        if (graph.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't " + "contain cycle");
    }

//    public static void main(String[] args) {
//        Graph graph = new Graph(7);
//        graph.addEdge(53949453, 12348888);
//        graph.addEdge(53945421, 12348888);
//        graph.addEdge(53915637, 12348888);
//        graph.addEdge(03077208, 53949453);
//        graph.addEdge(52962983, 53949453);
//        graph.addEdge(03077208, 53945421);
//        graph.addEdge(12348888, 53949453);
//        graph.addEdge(53949453, 08628407);
//        graph.addEdge(53949453, 03077208);
//        graph.addEdge(53949453, 52962983);
//
//
//        if (graph.isCyclic())
//            System.out.println("Graph contains cycle");
//        else
//            System.out.println("Graph doesn't " + "contain cycle");
//    }
}
