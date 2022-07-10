package com.mj.disJoint;

/**
 * @Author:ghq
 * @Date: 2022/04/26/0:20
 * @Description
 */
public class UnionFind_QU extends UnionFind{
    public UnionFind_QU(int capacity) {
        super(capacity);
    }
   //UF的查找是找根节点
    @Override
    public int find(int v) {
        //只有根节点的父节点是它自己，这个不难想把
        while(v!=parents[v]){
            v=parents[v];
        }
        return v;
    }

    @Override
    public void union(int v1, int v2) {
        int p1=find(v1);
        int p2=find(v2);
        if(p1==p2){return;}
        parents[p1]=p2;
        return;
    }
}
