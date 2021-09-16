package amazon;

/*
写了半天，还用了swap函数，看到第二个corner case的时候傻眼了
直接从后面开始遍历就可以了
* */

import java.util.Arrays;

public class lc88 {
    private int[] nums1, nums2;
    public void merge(int[] nums1, int m, int[] nums2, int n) {
//         corner case
        if(nums1 == null) nums1 = nums2;
        this.nums1 = nums1;
        this.nums2 = nums2;
        int p1 = m - 1, p2 = n - 1, totalEnd = m + n - 1;
        while (p1 >= 0 && p2 >= 0){
            if(nums1[p1] > nums2[p2]){
                nums1[totalEnd] = nums1[p1];
                totalEnd--;
                p1--;
            }else {
                nums1[totalEnd] = nums2[p2];
                totalEnd--;
                p2--;
            }
        }
        if(p1 < 0)System.arraycopy(nums2,0,nums1,0,p2 + 1);
    }


    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        new lc88().merge(nums1, 3,nums2,3);
        //打印数组要注意要用arrays.toString,否则智能打印地址，一定要记得！！！！
        System.out.println(Arrays.toString(nums1));


//        int[] nums1 = {4,5,6,0,0,0};
//        int[] nums2 = {1,2,3};
//        new lc88().merge(nums1, 3,nums2,3);
//        System.out.println(Arrays.toString(nums1));
    }
}
