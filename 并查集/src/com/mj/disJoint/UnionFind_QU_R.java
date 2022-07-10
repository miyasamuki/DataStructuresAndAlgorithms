package com.mj.disJoint;

/**
 * @Author:ghq
 * @Date: 2022/04/26/21:50
 * @Description
 */
public class UnionFind_QU_R extends UnionFind{
    //基于UnionFind_QU的优化
    int[] ranks;
    public UnionFind_QU_R(int capacity) {
        super(capacity);
        ranks=new int[capacity];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i]=1;
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
        if (ranks[p1]>ranks[p2]) {
            parents[p2]=p1;
        }else if(ranks[p1]<ranks[p2]){
            parents[p1]=p2;
        }else{
            parents[p2]=p1;
            ranks[p1]++;
        }
    }
}
