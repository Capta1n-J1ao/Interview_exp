package amazon;

import java.util.Arrays;

/*
一开始想到的思路和这个题解一样，但是代码一直写不好，要注意下面test case的情况
那就是如果nums的长度为偶数，会出现环，这时候需要判断，如果新的index是起点，那么就要改变index++
但是具体的实现是有tricky成分的
https://leetcode-cn.com/problems/rotate-array/solution/javashi-xian-zhi-jie-jiao-huan-kong-jian-fu-za-du-/

关于翻转的题解，写的比较清楚的，感觉代码还是挺简单的，主要这个方法挺难想的：
https://leetcode-cn.com/problems/rotate-array/solution/shu-zu-fan-zhuan-xuan-zhuan-shu-zu-by-de-5937/
* */

public class lc189 {
//    public void rotate(int[] nums, int k) {
//        /*
//        这道题目参数的含义一定要搞清楚，其中index指的是将要替换别人的下标，index1是将要被替换的下标
//        temp是将要替换别人的元素，temp1是将要被替换的需要保存的元素
//        isLoop的作用就是为了判断test case中存在的环，这个方法非常巧妙
//        * */
//        int numLen = nums.length, count = 0, index = 0, temp = nums[index], isLoop = 0;
////        这个才是真正的corner case
//        if(k % numLen == 0 || numLen == 1) return;
//        while (count < numLen){
//            int index1 = (index + k) % numLen;
//            int temp1 = nums[index1];
//            nums[index1] = temp;
////            System.out.println("index1 = " + index1 + " temp = " + temp + " temp1 = " + temp1);
////            System.out.println(Arrays.toString(nums));
//            temp = temp1;
//            index = index1;
////            这个判断条件至关重要！就是为了判断最重是不是一个环！！
////            而环的判断条件是走了一圈是不是走到了原点，原点就用isLoop来标记
//            if(index == isLoop){
//                index++;
//                isLoop = index;
//                temp = nums[index];
//            }
//            count++;
//        }
//    }

//    方法二：直接用翻转的方法来做，更加好理解，但是方法难想到
    private int[] nums;
    private int k;
    public void rotate(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        int numLen = nums.length, count = 0, index = 0, temp = nums[index], isLoop = 0;
//        这个才是真正的corner case
        if(k % numLen == 0 || numLen == 1) return;
//        专门针对第二个test case
        k = k % numLen;
        reverse(0, numLen - 1);
        reverse(0, k - 1);
        reverse(k, numLen - 1);
    }

    private void reverse(int start, int end){
//        System.out.println(start + " " + end);
        int left = start, right = end;
        while (left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
//        int[] test = {-1,-100,3,99};
//        new lc189().rotate(test, 2);
//        System.out.println(Arrays.toString(test));

//        此case仅针对方法二：
        int[] test = {1,2,3};
        new lc189().rotate(test, 4);
        System.out.println(Arrays.toString(test));
    }
}
