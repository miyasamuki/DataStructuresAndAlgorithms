package com.mj.disJoint;

/**
 * @Author:ghq
 * @Date: 2022/04/26/20:46
 * @Description
 */
public class UnionFind_QU_S extends UnionFind{
    //基于UnionFind_QU的优化
    int[] sizes;
    public UnionFind_QU_S(int capacity) {
        super(capacity);
        sizes=new int[capacity];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i]=1;
        }
    }
    @Override  //UF的查找是找根节点
    public int find(int v) {
        //只有根节点的父节点是它自己，这个不难想把
        while(v!=parents[v]){
            v=parents[v]; }
        return v; }
    @Override
    public void union(int v1, int v2) {
        int p1=find(v1);
        int p2=find(v2);
        if(p1==p2){return;}
        if (sizes[p1]>=sizes[p2]) {
            parents[p2]=p1;
            sizes[p1]+=sizes[p2];
        }else if(sizes[p1]<sizes[p2]){
            parents[p1]=p2;
            sizes[p2]+=sizes[p1];
        }
    }
}
