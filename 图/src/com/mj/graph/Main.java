package com.mj.graph;

import sun.security.provider.certpath.Vertex;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author:ghq
 * @Date: 2022/05/01/10:46
 * @Description
 */
public class Main {
    static Graph.WeightManager<Double> weightManager=new Graph.WeightManager<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            return w1.compareTo(w2);
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1+w2;
        }

        @Override
        public Double zero() {
            return 0.0;
        }


    };
    public static void main(String[] args) {
        //假设顶点存着String，权重是Integer
      //  Graph<String, Double> graph = new ListGraph<>(weightManager);
//        graph.addEdge("v1","v0",9);
//        graph.addEdge("v0","v4",6);
//        graph.addEdge("v3","v4",1);
//        graph.addEdge("v2","v3",5);
//        graph.addEdge("v1","v2",3);
//        graph.addEdge("v2","v0",2);
//        ((ListGraph<String, Integer>) graph).print();
//        graph.removeVertex("v0");
//        ((ListGraph<String, Integer>) graph).print();
        /**
         * 用有向图表示无向图是简单的，
         * 只需要同时
         *  graph.addEdge("v1","v0",9);
         *   graph.addEdge("v0","v1",9);就好
         */
//        graph.addEdge("V1", "V0", 9);
//        graph.addEdge("V1", "V2", 3);
//        graph.addEdge("V2", "V0", 2);
//        graph.addEdge("V2", "V3", 5);
//        graph.addEdge("V3", "V4", 1);
//        graph.addEdge("V0", "V4", 6);
//        graph.bfs("V1");
        //testmst();
        testMultiSp();
    }
    static void testSp(){
        Graph<Object,Double> graph=directedGraph(Data.NEGATIVE_WEIGHT1);
        Map<Object, Graph.PathInfo<Object,Double>> map = graph.ShortestPathDijksra("A");
        map.forEach((Object ob, Graph.PathInfo pathInfo)->{
            System.out.println(ob+""+pathInfo);
        });
    }
    static void testMultiSp(){
        Graph<Object, Double> graph = directedGraph(Data.SP);
        Map<Object,Map<Object, Graph.PathInfo<Object,Double>>> sp=graph.shortestPathFloyd();
        sp.forEach((Object from,Map<Object, Graph.PathInfo<Object,Double>> paths)->{
            System.out.println(from+"------------------------");
            paths.forEach((Object to,Graph.PathInfo<Object,Double> path)->{
                System.out.println(to+"-"+path);
            });

        });

    }
    static void testTopo(){
        Graph<Object,Double> graph=directedGraph(Data.TOPO);
        List<Object> list=graph.topologicSort();
        System.out.println(list);
    }
    public static void testDirectedGraph(){
        Graph<Object, Double> graph = directedGraph(Data.DFS_02);
        graph.bfs("c", new Graph.VertexedVisted() {
            @Override
            public void visit(Object o) {
                System.out.println(o);
                System.out.println("排序了嘛");
            }

        });
    }
    public static void testmst(){
        Graph<Object, Double> graph = undirectedGraph(Data.MST_01);
        Set<Graph.EdgeInfo<Object, Double>> infos = graph.mst();
        for (Graph.EdgeInfo<Object,Double> info: infos) {
            System.out.println(info);
        }

    }

    /**
     * 有向图：给我一个二维数组，还你一张图
     *
     * @param data
     * @return
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     *
     * @param data
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }
}
