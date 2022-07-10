package com.mj.graph;


import java.util.*;

/**
 * @Author:ghq
 * @Date: 2022/04/30/23:12
 * @Description
 */
public abstract class Graph<V,E extends Comparable> {
    public Graph(){}
    public Graph(WeightManager<E> weightManager){
        this.weightManager=weightManager;
    }
    public abstract Map<V,Map<V,PathInfo<V,E>>> shortestPathFloyd();
    //最短路径
    //来一个添加路径信息的最短路径
    public abstract Map<V,PathInfo<V,E>> ShortestPathDijksra(V begin);
    public static class PathInfo<V,E>{
        protected E weight;

        protected List<EdgeInfo<V,E>> edgeInfos=new LinkedList<>();
        @Override
        public String toString() {
            return "PathInfo{" +
                    "weight=" + weight +
                    ", edgeInfos=" + edgeInfos +
                    '}';
        }
    }

    public abstract Map<V, PathInfo<V,E>> bellmanFord(V begin);
    //图需要添加什么接口呢
    //查找边和顶点数量的
    public abstract int edgeSize();
    public abstract int verticesSize();
    //1.添加顶点
    public abstract void addVertex(V v);
    //2.添加边
    public abstract void addEdge(V from,V to,E weight);
    public abstract void addEdge(V from,V to);
    //3.删除顶点
    public abstract void removeVertex(V v);
    public abstract void removeEdge(V from,V to);
    public abstract void bfs(V begin,VertexedVisted vertexedVisted);
    public abstract void dfs(V begin);
    interface VertexedVisted<V>{
        public void visit(V v);
    }
    /**
     * 拓扑排序
     * @return
     */
    public abstract List<V> topologicSort();
    //由于Edge和Vertex是不对外暴露的，对外暴露的只有V，E
    static class EdgeInfo<V,E>{
        private V from;
        private V to;
        private E weight;
        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }
    }
    public abstract Set<EdgeInfo<V, E>> mst();
    protected WeightManager<E> weightManager;
    public interface WeightManager<E>{
        int compare(E w1,E w2);
        E add(E w1,E w2);
        E zero();

    }


}
