package amazon;

/*
唯一难点就是那个while循环的判断条件，一定要搞清楚
* */

import java.util.Arrays;

public class lc905 {
    private int[] nums;
    public int[] sortArrayByParity(int[] nums) {
        this.nums = nums;
        int numLen = nums.length;
        int left = 0, right = numLen - 1;
        while (left < right){
//            这个while之前也碰到过这个问题，一定要注意！！！！
            while (left < right && nums[left] % 2 == 0) left++;
            while (left < right && nums[right] % 2 == 1) right--;
            swap(left, right);
        }
        return nums;
    }

    private void swap(int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] test = {3,1,2,4};
        System.out.println(Arrays.toString(new lc905().sortArrayByParity(test)));

//        int[] test = {0,2};
//        System.out.println(Arrays.toString(new lc905().sortArrayByParity(test)));
    }
}
