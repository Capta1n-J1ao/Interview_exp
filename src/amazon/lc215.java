package amazon;

/*
快排和大小堆这两个方法一定要都会
快排这个解法有一个视频讲的非常清楚：
https://www.bilibili.com/video/BV15Z4y1p7KR?t=412

还有就是对于大小顶堆讲的最好的出处：
https://blog.csdn.net/qq_41682302/article/details/95910651
* */

import java.util.Random;

public class lc215 {
    private int[] nums;
    private int numLen;
    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        numLen = nums.length;
//        这样做的好处是能够把nums的下标和target从0开始一一对应
        int target = numLen - k;
        int left = 0, right = numLen - 1;
        int index = -1;
        while (index != target){
            index = patition(left, right);
            if(index < target){
                left = index + 1;
            }else if(index > target){
                right = index - 1;
            }else break;
        }

        return nums[target];
    }

//    private int patition(int left, int right){
//        int mid = nums[right];
//        int lo = left;
//        int hi = right - 1;
//        while (lo < hi){
//            while (lo < hi && nums[lo] <= mid) lo++;
//            while (lo < hi && nums[hi] >= mid) hi--;
//            swap(lo, hi);
//        }
//        if(nums[lo] < mid) {
//            return right;
//        }
//        swap(lo, right);
//        return lo;
//    }


//  随机数版本
//    private int patition(int left, int right){
//        int lo = left, hi = right - 1, pivot = left  + (int)(Math.random() * (right - left + 1));
//        swap(pivot, right);
//        while (lo < hi){
//            while (lo < hi && nums[lo] <=  nums[right]) lo++;
//            while (lo < hi && nums[hi] >  nums[right]) hi--;
//            swap(lo, hi);
//        }
//        if(nums[lo] > nums[right]){
//            swap(lo, right);
//            return lo;
//        }
//
//        return right;
//    }


//    2021/6/14 二刷
    private int patition(int left, int right){
        int l = left, r = right - 1;
        Random random = new Random();
//        注意nextInt这个命令里面的数必须为正数，所以必须要有这个+1，否则就不对了
        int randomNum = random.nextInt(right - left + 1);
//        System.out.println(randomNum + " " + left + " " + right);
        int pivot = left + randomNum, target = nums[pivot];
        swap(pivot, right);
        while (l < r){
            while (l < r && nums[l] <= target) l++;
            while (l < r && nums[r] > target) r--;
            swap(l, r);
        }
//        System.out.println("end " + randomNum + " " + left + " " + right);
        if(nums[l] > target) {
            swap(l, right);
            return l;
        }
        return right;
    }

    private void swap(int start, int end){
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    public static void main(String[] args) {
//        int[] nums = {3, 2, 1, 5, 6, 4};
//        int findLargest = new lc215().findKthLargest(nums, 2);
//        System.out.println(findLargest);

//        int[] nums = {3, 3, 3, 3, 3, 3, 3, 3, 3};
//        int findLargest = new lc215().findKthLargest(nums, 1);
//        System.out.println(findLargest);

//        int[] nums = {104, 80, 149, 65};
//        int findLargest = new lc215().findKthLargest(nums, 3);
//        System.out.println(findLargest);

        int[] nums = {-1,2,0};
        int findLargest = new lc215().findKthLargest(nums, 2);
        System.out.println("答案为 0， 代码答案为：" + findLargest);
    }
}
