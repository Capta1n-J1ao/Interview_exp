package amazon;

/*
这道题目常规方法就是用hashMap来做，没有难度，主要思考进阶
1. 给定数组已经排序---使用双指针即可
2. 如果nums1的大小比nums2小很多---
3. 如果nums2的元素存储在磁盘上，内存是有限的，那么用hashMap的方法更为稳妥

本题解只写双指针版本，其他看下官方题解即可
* */

import java.util.*;

public class lc350 {
    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> resList = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0, index2 = 0;
        int nLen1 = nums1.length;
        int nLen2 = nums2.length;
        while (index1 < nLen1 && index2 < nLen2){
            if (nums1[index1] == nums2[index2]){
                resList.add(nums1[index1]);
                index1++;
                index2++;
            }else if (nums1[index1] < nums2[index2])
                index1++;
            else if (nums1[index1] > nums2[index2])
                index2++;
        }
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) res[i] = resList.get(i);
        return res;
    }

    public static void main(String[] args) {
        int[] test1 = {1,2,2,1};
        int[] test2 = {2,2};
        System.out.println(Arrays.toString(new lc350().intersect(test1, test2)));
    }
}
