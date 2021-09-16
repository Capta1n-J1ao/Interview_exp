package amazon;

/*
这道题目和lc33必须一起做，太像，而且corner case非常多，真的很难全想清楚
建议还是先做lc33，把liweiwei的题解看一遍基本就能搞清楚了,我就是看了liweiwei的代码然后直接写出来了：
https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/er-fen-fa-python-dai-ma-java-dai-ma-by-liweiwei141/
但是具体代码还是要看这道题目的，下面这个文字版的说的挺清楚：
https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/solution/yi-wen-dai-ni-gao-ding-er-fen-sou-suo-ji-ki52/
* */

public class lc81 {
    public boolean search(int[] nums, int target) {
        int numLen = nums.length;
        int left = 0, right = numLen - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target || nums[left] == target || nums[right] == target) return true;
            if(nums[mid] == nums[left] && nums[mid] == nums[right]){
                left++;
                right--;
            }else if(nums[mid] <= nums[right]){
                if(nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid;
            }else {
                if(nums[left] <= target && target < nums[mid]) right = mid;
                else left = mid + 1;
            }
        }
        return nums[left] == target;
    }

    public static void main(String[] args) {
        int[] test = {2,5,6,0,0,1,2};
        System.out.println("正确答案为 ： false， 代码答案 ： " + new lc81().search(test, 3));

        int[] test1 = {2,5,6,0,0,1,2};
        System.out.println("正确答案为 ： true， 代码答案 ： " + new lc81().search(test1, 0));

        int[] test2 = {1,0,1,1,1,1};
        System.out.println("正确答案为 ： true， 代码答案 ： " + new lc81().search(test2, 0));

        int[] test3 = {1};
        System.out.println("正确答案为 ： true， 代码答案 ： " + new lc81().search(test3, 1));

        int[] test4 = {4,5,6,7,0,1,2};
        System.out.println("正确答案为 ： true， 代码答案 ： " + new lc81().search(test4, 6));
    }
}
