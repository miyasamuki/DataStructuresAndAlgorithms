package com.mj;

import java.util.Arrays;

/**
 * @Author:ghq
 * @Date: 2022/04/13/16:29
 * @Description
 */
public abstract class Sort<E extends Comparable> {
    protected E[] array;

    /**
     * 比较次数
     * @param array
     */
    protected int cmpCount;
    protected int swapCount;

    public void sort(E[] array1){
        if(array1==null||array1.length<2) {return;}
        this.array=array1;
        sort();
    }
    protected abstract void sort();
    protected int cmp(int i1,int i2){
        cmpCount++;
        return array[i1].compareTo(array[i2]);

    }
    protected int cmp(E i1,E i2){
        cmpCount++;
        return i1.compareTo(i2);

    }
    protected void swap(int i1,int i2){
        swapCount++;
        E temp=array[i1];
        array[i1]=array[i2];
        array[i2]=temp;

    }

    public boolean isStable(){
        Person[] persons=new Person[20];
        for (int i = 0; i < 20; i++) {
            persons[i]=new Person(10,10*i);
        }
        sort((E[])persons);
        for (int i = 0; i < 20; i++) {
            if(persons[i].getScore()!=persons[i-1].getScore()+10){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "array=" + Arrays.toString(array) +
                ", cmpCount=" + cmpCount +
                ", swapCount=" + swapCount +
                '}';
    }
}
