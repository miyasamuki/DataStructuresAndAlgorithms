package 集合;

import java.util.Arrays;
import java.util.HashSet;

public class 求并集 {
    public static void main(String[] args) {
        int[] array1=new int[]{1,2,2,1};
        int[] array2=new int[]{2,2};
        int[] newArray=intersection(array1,array2);

        System.out.println(Arrays.toString(newArray));
    }
    public static int[] intersection(int[] array1, int[] array2){
        HashSet<Integer> set1=new HashSet<>();
        HashSet<Integer> set2=new HashSet<>();
        for(int num:array1){
            set1.add(num);
        }
        for(int num:array2){
            set2.add(num);
        }
        return getIntersection(set1,set2);
    }

    private static int[] getIntersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> set=set1.size()> set2.size()?set1:set2;
        HashSet<Integer> newSet=new HashSet<>();
        for(int num:set){
            if(set1.contains(num)&&set2.contains(num)){
                newSet.add(num);
            }
        }
        int[] newarrays=new int[newSet.size()];
        int index=0;
        for(int num:newSet){
            newarrays[index++]=num;
        }
        return newarrays;
    }
}
