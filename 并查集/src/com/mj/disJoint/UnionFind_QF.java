package com.mj.disJoint;

/**
 * @Author:ghq
 * @Date: 2022/04/25/23:23:采用Quick Find的思路实现
 * @Description
 */
public class UnionFind_QF extends UnionFind {
    public UnionFind_QF(int capacity) {
        super(capacity);
    }
    @Override
    public int find(int v){
        rangeCheck(v);
        return parents[v];
    }
    @Override
    public void union(int v1, int v2){
        int p1=find(v1);
        int p2=find(v2);
        if(p1==p2) {return;}
        for (int i = 0; i < parents.length; i++) {
            if(parents[i]==p1){
                parents[i]=p2;
            }
        }
    }
}
