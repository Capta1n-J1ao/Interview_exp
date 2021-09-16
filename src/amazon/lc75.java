package amazon;

/*
这道题目其实想法挺难的，包括之前其实也只是在看了视频以后只是依葫芦画瓢做的
但是对实际的算法其实并不了解。
20210723重新刷的时候仔细思考了一下，总结了如下：
1. 这道题目第一反应就是用双指针，但是光有双指针是不够的的，没法涉及到三个数
2. 那么自然而然会想到用三个指针来实现双指针的办法
3. 根据liweiwei的题解，其实就是用两个指针去卡0和2，然后第三个指针去处理1

了解了以上思路，再去看题解，会清晰很多
题解看Liweiwei，讲的很清楚,而且要搞清楚这个循环不变量的意义：
https://leetcode-cn.com/problems/sort-colors/solution/kuai-su-pai-xu-partition-guo-cheng-she-ji-xun-huan/
liweiwei的算法可能看不懂，所以有个视频将挺好：
https://www.bilibili.com/video/BV1RE411X7M7?from=search&seid=9536623229783031606
* */

import java.util.Arrays;

public class lc75 {
    private int[] nums;
    public void sortColors(int[] nums) {
        this.nums = nums;
        int numLen = nums.length;
        if(numLen < 2) return;
        //循环不变量,因为三个区间都遵守[){[)[)的关系
        // 所以如下取值，zero的含义是zero左边的元素都为0
        // two的含义是two本身以及右边的元素都为2
        int zero = 0;
        int two = numLen;
        //循环变量i的作用其实就是简洁处理1，工作原理为：
        // 如果遇到0，先交换，然后zero+1，这样保证zero左边的元素都为0
        // 如果遇到2，先two-1，然后交换，这样保证two本身以及右边的元素都为2
        // 如果遇到1，那么i+1，这时候注意zero和two其实都是没变的，
        // 然后整个数组其实没有操作，
        // 但是要注意，一旦i又遇到0或者2，1就会被one或者two调换，
        // 在遇到0和2的时候交换顺序不同的原因就是区间左闭右开的一致性
        int i = 0;
        while (i < two){
            if(nums[i] == 0){
                swap(i, zero);
                i++;
                zero++;
            }else if(nums[i] == 1){
                i++;
            }else {
                two--;
                swap(i, two);
            }
        }
    }

    private void swap(int start, int end){
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }


    public static void main(String[] args) {
        int[] nums = {2, 0, 1, 2, 1, 0};
        //int[] nums = {2, 0, 1};
        new lc75().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
