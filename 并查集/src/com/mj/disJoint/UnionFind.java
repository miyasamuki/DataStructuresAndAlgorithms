package com.mj.disJoint;

/**
 * @Author:ghq
 * @Date: 2022/04/25/21:35
 * @Description
 */
public abstract class UnionFind {
    protected int []parents;
    public UnionFind(int capacity) {
        if(capacity<0){
            throw new IllegalArgumentException("capacity must>=1");
        }
        parents=new int[capacity];
        //对parents进行初始化
        for (int i = 0; i < parents.length; i++) {
            parents[i]=i;
        }
    }
    protected boolean isSame(int v,int v2){
        return find(v)==find(v2);
    }
    protected void rangeCheck(int v){
        if(v<0||v>=parents.length){
            throw new IllegalArgumentException("v is out of bound");
        }
    }
    public abstract int find(int v);
    public abstract void union(int v1, int v2);
}
