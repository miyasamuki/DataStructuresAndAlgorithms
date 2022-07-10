package com.mj.graph;

import java.util.*;

/**
 * @Author:ghq
 * @Date: 2022/05/01/9:47
 * @Description
 */

public class ListGraph<V,E  extends Comparable<E>> extends Graph<V,E>{
    private Map<V,Vertex<V,E>> vertices=new HashMap<>();
    private Set<Edge<V,E>> edges=new HashSet<>();
    public ListGraph(){

    }
    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }
    @Override
    public Map<V,Map<V,PathInfo<V,E>>> shortestPathFloyd(){
        Map<V,Map<V,PathInfo<V,E>>> paths=new HashMap<>();
        for(Edge<V,E> edge:edges){
            Map<V,PathInfo<V,E>> map=paths.get(edge.from.value);
            if(map==null) {
                map = new HashMap<>();
                paths.put(edge.from.value,map);
            }
            PathInfo<V, E> pathInfo = new PathInfo<>();
            pathInfo.edgeInfos.add(edge.info());
            pathInfo.weight= edge.weight;
            map.put(edge.to.value,pathInfo);
        }
        vertices.forEach(((V v1, Vertex<V,E> vertex1) ->{
            vertices.forEach((V v2,Vertex<V,E> vertex2)->{
                vertices.forEach((V v3,Vertex<V,E> vertex3)->{
                    if(v1.equals(v2)||v1.equals(v3)||v2.equals(v3)){return;}
                    //然后就是循环所有的节点，每一个节点都有可能是i,j,k
                    //然后对比，vi->vj;vi->Vk->vj的大小
                    //具体就是循环vi的出边，看有没有到vj的
                    PathInfo<V,E> pathInfo13=getPathInfo(v1,v3,paths);
                    if(pathInfo13==null){return;}
                    PathInfo<V,E> pathInfo32=getPathInfo(v3,v2,paths);
                    if(pathInfo32==null){return;}
                    PathInfo<V,E> pathInfo12=getPathInfo(v1,v2,paths);
                   E newWeight= weightManager.add(pathInfo13.weight, pathInfo32.weight);
                   if(pathInfo12!=null&&weightManager.compare(pathInfo12.weight,newWeight)<0){
                       return; }
                   PathInfo<V,E> newpathInfo=new PathInfo<>();
                   newpathInfo.edgeInfos.addAll(pathInfo13.edgeInfos);
                   newpathInfo.edgeInfos.addAll(pathInfo32.edgeInfos);
                   newpathInfo.weight=newWeight;
                    paths.get(v1).put(v2,newpathInfo);
                });
            });
        }));
        return paths;
    }
    private PathInfo<V,E> getPathInfo(V v1,V v2,Map<V,Map<V,PathInfo<V,E>>> paths){
        if(paths.get(v1)==null){
            return null;
        }
        return paths.get(v1).get(v2);
    }
//    @Override
//    public Map<V, E> shortestPath(V begin) {
//
//        //难点1;queue里面的权值最小怎么存
//        //难点2：比较器怎么存到堆里--->暂时不用堆
//        //难点3：权重怎么定义相加--->weightManager
//        //难点4：无穷大怎么表示------>用空表示
//        //难点5：我要判断没有负权环----
//        //然后我要从begin开始，循环begin的outVertex
//        Vertex<V,E> beginVertex=vertices.get(begin);
//        if(beginVertex==null){return null;}
//        Map<Vertex<V,E>,E> path=new HashMap<>();
//        Map<V,E> selectedPath=new HashMap<>();
//        //思路：selectedPath要有个配套了来记录最短路径的Set
//        //这里就是初始化我的path表
//        for (Edge<V,E> edge:beginVertex.outEdges) {
//            path.put(edge.to, edge.weight);
//        }
//        //然后找到path中最小的那个
//        while(!path.isEmpty()) {
//            Map.Entry<Vertex<V, E>, E> minEntry = shortestPath(path);
//            selectedPath.put(minEntry.getKey().value, minEntry.getValue());
//            path.remove(minEntry.getKey());
//            //然后循环这个最小的outEdges开始循环//也就松弛操作，就是更新outEdges.to的节点
//            relax(begin, path, selectedPath, minEntry);
//        }
//        return selectedPath;
//
//
//    }
    //松弛操作
    private void relax(V begin, Map<Vertex<V, E>, E> path, Map<V, E> selectedPath, Map.Entry<Vertex<V, E>, E> minEntry) {
        for (Edge<V, E> edge : minEntry.getKey().outEdges) {
            if(selectedPath.containsKey(edge.to.value)|| edge.to.value== begin){continue;}
            if (path.get(edge.to)==null||weightManager.compare(path.get(edge.to), weightManager.add(minEntry.getValue(), edge.weight)) > 0) {
                path.put(edge.to, weightManager.add(minEntry.getValue(), edge.weight));
            }
        }
    }

    private Map.Entry<Vertex<V,E>, E> shortestPath(Map<Vertex<V, E>, E> path) {
        Iterator<Map.Entry<Vertex<V,E>, E>> iterator = path.entrySet().iterator();
        Map.Entry<Vertex<V, E>, E> minEntry = iterator.next();
        while(iterator.hasNext()){
            Map.Entry<Vertex<V, E>, E> nextEntry = iterator.next();
            if(weightManager.compare(minEntry.getValue(),nextEntry.getValue())>0){
                minEntry=nextEntry;
            }

        }
        return minEntry;
    }
///////////////////////////////////////////////////////////////////////////
@Override
public Map<V, PathInfo<V,E>> bellmanFord(V begin){
    Vertex<V,E> beginVertex=vertices.get(begin);
    if(beginVertex==null){return null;}
    Map<V,PathInfo<V,E>> selectedPaths=new HashMap<>();
    //思路：selectedPath要有个配套了来记录最短路径的Set
    //这里就是初始化我的path表
    PathInfo<V,E> beginPath=new PathInfo<>();
    beginPath.weight= weightManager.zero();
    selectedPaths.put(begin,beginPath);
    int count=vertices.size()-1;
    for (int i = 0; i < count; i++) {
        for (Edge<V,E> edge:edges) {//bellmanFord是从我所有的边中选出一条开始松弛的，因此并不需要初始化
             PathInfo<V,E> fromPath=selectedPaths.get(edge.from.value);
             if(fromPath==null){continue;}
            relaxbellmanFord(edge,fromPath,selectedPaths);
        }
    }
    selectedPaths.remove(begin);//将begin-begin的那条边删掉
    return selectedPaths;
}
    private void relaxbellmanFord(Edge<V,E> edge,PathInfo<V,E> fromPath,Map<V,PathInfo<V,E>> selectedPaths) {
        if(selectedPaths.get(edge.to.value)!=null&&weightManager.compare(selectedPaths.get(edge.to.value).weight, weightManager.add(fromPath.weight, edge.weight)) <=0){
           return;
        }
        if(selectedPaths.get(edge.to.value)==null){
            selectedPaths.put(edge.to.value,new PathInfo<>());

        }
            selectedPaths.get(edge.to.value).weight= weightManager.add(fromPath.weight, edge.weight);
            selectedPaths.get(edge.to.value).edgeInfos.clear();
            selectedPaths.get(edge.to.value).edgeInfos.addAll(fromPath.edgeInfos);
            selectedPaths.get(edge.to.value).edgeInfos.add(edge.info());
    }
    @Override
    public Map<V, PathInfo<V,E>> ShortestPathDijksra(V begin) {

        //难点1;queue里面的权值最小怎么存
        //难点2：比较器怎么存到堆里--->暂时不用堆
        //难点3：权重怎么定义相加--->weightManager
        //难点4：无穷大怎么表示------>用空表示
        //难点5：我要判断没有负权环----
        //然后我要从begin开始，循环begin的outVertex
        Vertex<V,E> beginVertex=vertices.get(begin);
        if(beginVertex==null){return null;}
        Map<Vertex<V,E>, PathInfo<V,E>> path=new HashMap<>();
        Map<V,PathInfo<V,E>> selectedPath=new HashMap<>();
        //思路：selectedPath要有个配套了来记录最短路径的Set
        //这里就是初始化我的path表
        for (Edge<V,E> edge:beginVertex.outEdges) {
            PathInfo<V,E> pathInfo=new PathInfo<>();
            pathInfo.edgeInfos.add(edge.info());
            pathInfo.weight= edge.weight;
            path.put(edge.to, pathInfo);

        }
        //然后找到path中最小的那个
        while(!path.isEmpty()) {
            Map.Entry<Vertex<V, E>, PathInfo<V,E>> minEntry = shortestPath2(path);
            selectedPath.put(minEntry.getKey().value, minEntry.getValue());
            path.remove(minEntry.getKey());
            //然后循环这个最小的outEdges开始循环//也就松弛操作，就是更新outEdges.to的节点
            for (Edge<V, E> edge : minEntry.getKey().outEdges) {
                if(selectedPath.containsKey(edge.to.value)|| edge.to.value== begin){continue;}
                relaxDijksra(path, minEntry, edge);
            }
        }
        return selectedPath;


    }

    private void relaxDijksra(Map<Vertex<V, E>, PathInfo<V, E>> path, Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry, Edge<V, E> edge) {
        if(path.get(edge.to)==null){
            path.put(edge.to,new PathInfo<>());
        }
        if (path.get(edge.to).weight==null||weightManager.compare(path.get(edge.to).weight, weightManager.add(minEntry.getValue().weight, edge.weight)) > 0) {

            path.get(edge.to).weight= weightManager.add(minEntry.getValue().weight, edge.weight);
            path.get(edge.to).edgeInfos.clear();
            path.get(edge.to).edgeInfos.addAll(minEntry.getValue().edgeInfos);
            path.get(edge.to).edgeInfos.add(edge.info());

        }
    }
    //选最小的那条边
    private Map.Entry<Vertex<V,E>,PathInfo<V,E>> shortestPath2(Map<Vertex<V, E>, PathInfo<V,E>> path) {
        Iterator<Map.Entry<Vertex<V,E>, PathInfo<V,E>>> iterator = path.entrySet().iterator();
        Map.Entry<Vertex<V, E>, PathInfo<V,E>> minEntry = iterator.next();
        while(iterator.hasNext()){
            Map.Entry<Vertex<V, E>, PathInfo<V,E>> nextEntry = iterator.next();
            if(weightManager.compare(minEntry.getValue().weight,nextEntry.getValue().weight)>0){
                minEntry=nextEntry;
            }

        }
        return minEntry;

    }
//////////////////////////////////////////////////////////////////////////
    public void print(){
        System.out.println("[顶点]");
        vertices.forEach((V v,Vertex<V,E> vertex)->{
            System.out.println(v);
            System.out.println("out----------------");
            System.out.println(vertex.outEdges);
            System.out.println("in------------------");
            System.out.println(vertex.inEdges);
        });
        System.out.println("[边]");
        edges.forEach((Edge<V,E> edge)->{
            System.out.println(edge);
        });
    }
    @Override
    public int edgeSize() {
        //那么这个边应该怎么统计呢，要不我们就搞一个map来统计，边有几条吧
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if(vertices.containsKey(v)) {return;}
        vertices.put(v,new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        //首先我们需要由V找到vertex,因此我们需要一个map来存
        Vertex<V,E> fromVertex=vertices.get(from);
        if(fromVertex==null){
            fromVertex=new Vertex<>(from);
            vertices.put(from,fromVertex);
        }
        Vertex<V,E> toVertex=vertices.get(to);
        if(toVertex==null){
            toVertex=new Vertex<>(to);
            vertices.put(to,toVertex);
        }
        Edge<V,E> edge=new Edge<>(fromVertex,toVertex);
      //  if(fromVertex.outEdges.contains(edge)){
            //如果存在，则更新权重
            //但是如果将以前的边拿出来的话，会非常麻烦，首先hashSet中的值想拿出来，就智能循环遍历
            //所以不如就直接删掉，然后重新放
        //}
        edge.weight=weight;
        //将与edge 相等的家伙删掉,remove这个方法是，如果存在就删掉，返回true,如果不存在就返回false
       if( fromVertex.outEdges.remove(edge)){
        toVertex.inEdges.remove(edge);
        edges.remove(edge);
       }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);

    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to,null);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V,E> vertex=vertices.get(v);
        if(vertex==null){return;}
        //首先需要遍历所有的v,来找到与这个vertex相连的边,是不是傻，直接outEdges,和inEdges不就好了
//        vertices.forEach((V v1, Vertex<V,E> vertex1)->{
//            //v1做from v做to
//             edges.remove(new Edge<>(vertex1,vertex));
//            //v做from v做to
//            edges.remove(new Edge<>(vertex,vertex1));
//
//        });
        //这种方式是删不干净的，主要是因为别的节点里的outEdges和inEdges也可能有v0
//        vertex.outEdges.forEach((Edge<V,E> edge)->{//注意注意！！！一边遍历一遍删东西是不行的，这种容易出问题，最好使用迭代器
//            edges.remove(edge);                并且vertex中的Set也没有被删掉
//        });
//        vertex.inEdges.forEach((Edge<V,E> edge)->{
//            edges.remove(edge);
//        });
        for(Iterator<Edge<V,E>> iterator=vertex.outEdges.iterator();iterator.hasNext();){
             Edge<V,E> edge=iterator.next();
             edge.to.inEdges.remove(edge);
             iterator.remove();//将当前遍历到的元素从当前遍历的集合中删除，这样是安全的
            edges.remove(edge);
        }
        for(Iterator<Edge<V,E>> iterator=vertex.inEdges.iterator();iterator.hasNext();){
            Edge<V,E> edge=iterator.next();
            edge.from.outEdges.remove(edge);
            iterator.remove();//将当前遍历到的元素从当前遍历的集合中删除，这样是安全的
            edges.remove(edge);
        }
        if(vertices.containsKey(v)){
            vertices.remove(v);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V,E> fromVertex=vertices.get(from);
        Vertex<V,E> toVertex=vertices.get(to);

        if(fromVertex==null||toVertex==null){return;}
              Edge<V,E> edge=new Edge(fromVertex,toVertex);
              if(fromVertex.outEdges.remove(edge)){
                  toVertex.inEdges.remove(edge);
                  edges.remove(edge);
              }


    }

    /**
     * 广度优先搜索
     * @param begin
     */
    @Override
    public void bfs(V begin,VertexedVisted vertexedVisted) {
        //1.获得要遍历的节点
        Vertex<V,E> vertex=vertices.get(begin);
        if(vertex==null){return;}
        Set<Vertex<V,E>> visited=new HashSet<>();
        //java的queue是linkedlist实现的
        Queue<Vertex> queue=new LinkedList<>();
        queue.offer(vertex);
        while(!queue.isEmpty()){
            vertex= queue.poll();
            if(visited.contains(vertex)){ continue; }
            vertexedVisted.visit(vertex);
            visited.add(vertex);
            for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();) {
                Edge<V, E> next = iterator.next();
                //if(visited.contains(vertex)){ continue; }
                queue.offer(next.to);
            }
        }
    }
    @Override
    public void dfs(V begin) {
        //1.获得要遍历的节点
        Vertex<V,E> vertex=vertices.get(begin);
        if(vertex==null){return;}
        nonRecursion_dfs(vertex,new HashSet<>());

    }
    /**
     * 拓扑排序
     * @return
     */
    @Override
    public List<V> topologicSort() {
        Queue<Vertex<V,E>> queue=new LinkedList<>();
        Map<Vertex<V,E>,Integer> map=new HashMap<>();
        List<V> list=new ArrayList<>();
        vertices.forEach((V v,Vertex<V,E> vertex)->{
            int in=vertex.inEdges.size();
            if(in==0){
                queue.offer(vertex);
            }else{
                map.put(vertex,in);
            }
        });
        while (!queue.isEmpty()){
            Vertex<V,E> vertex= queue.poll();
            list.add(vertex.value);
            for (Edge<V,E> edge:vertex.outEdges) {
                Integer in = map.get(edge.to);
                in--;
                if(in==0){
                    queue.offer(edge.to);
                }else{
                    map.put(edge.to,in);
                }
            }
        }
        return  list;
    }

    private void dfs(Vertex<V, E> vertex,Set<Vertex<V,E>> visited) {
        //注意不可以在递归中使用集合，数组这种，会被刷新的，应该从外面传进来
        //Set<Vertex<V,E>> visited=new HashSet<>();
        //java的queue是linkedlist实现的

        System.out.println(vertex);
        visited.add(vertex);
        if(vertex.outEdges==null|| vertex.outEdges.isEmpty()){
            return;
        }
        for (Edge<V,E> edge: vertex.outEdges) {
            if(visited.contains(edge.to)){
                continue;
            }
            dfs(edge.to,visited);
        }
    }
   private Comparator<Edge<V,E>> edgeComparator=(Edge<V,E> e1,Edge<V,E> e2)->{
     return weightManager.compare(e2.weight, e1.weight);
   };
    @Override
    public Set<EdgeInfo<V, E>> mst() {
       // return prim();
        return Math.random()>0.5?prim(): Kruskal();
    }

    private Set<EdgeInfo<V, E>> prim() {
        Queue<Vertex<V,E>> queue=new LinkedList<>();
        Set<EdgeInfo<V,E>> edgeInfos=new HashSet<>();
        Set<Vertex<V,E>> visitedVertex=new HashSet();
        //随机从一个顶点开始,vertices.values()这个是hashmap的函数，.iterator()这个是获取迭代器。.next是获取任意一个值
        if(!vertices.values().iterator().hasNext()){return null;}
        Vertex<V,E> vertex=vertices.values().iterator().next();
        visitedVertex.add(vertex);
        com.mj.graph.priorityQueue.Queue heap =new com.mj.graph.priorityQueue.PriorityQueue<Edge<V,E>>(vertex.outEdges,edgeComparator);
        int edgeSize=vertices.size()-1;
        while(!heap.isEmpty()&&edgeInfos.size()<edgeSize) {
               Edge<V,E> minEdge= (Edge<V, E>) heap.deQueue();
               //由于第一次A的4这条边可能被排除了，但是加入B的时候，循环B的出度，4这条边可能又被加进来了，所以我们需要记录加过的边
            if(visitedVertex.contains(minEdge.to)) { continue;}
            edgeInfos.add(minEdge.info());
            //由于是无向图，所以你排除边的时候，不能这样干，如果是这样的话A->F,F->A就要排除两遍
            visitedVertex.add(minEdge.to);
            for (Edge<V,E> edge:minEdge.to.outEdges) {
                   heap.enQueue(edge);
               }
           }
        return edgeInfos;
    }
    private Set<EdgeInfo<V, E>> Kruskal() {
        if(vertices.size()-1<=0){return null;}
        Set<EdgeInfo<V,E>> edgeInfos=new HashSet<>();
        GenericUnionFind<V> genericUnionFind=new GenericUnionFind<>();
        vertices.forEach((V v,Vertex<V,E> vertex)->{
            //初始化所有顶点，让顶点全都自成集合
            genericUnionFind.mapset(v);
        });
        com.mj.graph.priorityQueue.Queue heap =new com.mj.graph.priorityQueue.PriorityQueue<Edge<V,E>>(edges,edgeComparator);
        while(!heap.isEmpty()&&edgeInfos.size()<vertices.size()-1){
            Edge<V,E> edge = (Edge<V, E>) heap.deQueue();
            if(genericUnionFind.isSame(edge.from.value,edge.to.value)){continue;}
            edgeInfos.add(edge.info());
            genericUnionFind.union(edge.from.value,edge.to.value);
        }
        return edgeInfos;
    }


    private void nonRecursion_dfs(Vertex<V, E> vertex,Set<Vertex<V,E>> visited){
        Stack<Vertex<V,E>> stack=new Stack<>();
        stack.push(vertex);
       System.out.println(vertex);
       visited.add(vertex);
       while (!stack.isEmpty()){
           Vertex<V,E> pop=stack.pop();
           if(pop.outEdges==null||pop.outEdges.isEmpty()){continue;}
           for (Edge<V,E> edge:pop.outEdges) {
               if(visited.contains(edge.to)){continue;}
               stack.push(edge.from);
               stack.push(edge.to);
               System.out.println(edge.to);
               visited.add(edge.to);
               break;
           }
       }
   }

    private static class Vertex<V,E>{
             Set<Edge<V,E>> inEdges=new HashSet<>();//因为边是不考虑顺序的，arrayList是有顺序的，所以这里用Set
             Set<Edge<V,E>> outEdges=new HashSet<>();;
             V value;
             int indegree;

        @Override
        public String toString() {
            return "Vertex{" + " value=" + value + '}';
        }

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {return true;}
            if (o == null || getClass() != o.getClass()) {return false;}
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value);
        }
        @Override
        public int hashCode() {
            return value==null?0:value.hashCode();
        }


    }
         private static class Edge<V,E>  {
               Vertex<V,E> from;
               Vertex<V,E> to;
               E weight;//权重
             @Override
             public String toString() {
                 return "Edge{" +
                         "from=" + from +
                         ", to=" + to +
                         ", weight=" + weight +
                         '}';
             }
             public EdgeInfo<V,E> info(){
                 return new EdgeInfo<V, E>(from.value, to.value,weight);
             }

             public Edge(Vertex<V, E> from, Vertex<V, E> to) {
                 this.from = from;
                 this.to = to;
             }
             @Override
             public boolean equals(Object o) {
                 if (this == o) {return true;}
                 if (o == null || getClass() != o.getClass()) {return false;}
                 Edge<?, ?> edge = (Edge<?, ?>) o;
                 return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
             }

             @Override
             public int hashCode() {
                 return Objects.hash(from, to, weight);
             }

         }
}
