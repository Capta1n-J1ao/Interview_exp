package amazon;

/*
这两道题目有点像，但是做法又不一样，建议先做11再做42

这道题目的难点在于怎么证明双指针是正确的，参考题解：
https://leetcode-cn.com/problems/container-with-most-water/solution/on-shuang-zhi-zhen-jie-fa-li-jie-zheng-que-xing-tu/
* */

public class lc11 {
    public int maxArea(int[] height) {
        int hLen = height.length, left = 0, right = hLen - 1, res = 0, edge = Integer.MAX_VALUE;
        while (left < right){
            edge = Math.min(height[left], height[right]);
            res = Math.max(res, edge * (right - left));
            if(height[left] < height[right]) left++;
            else right--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {1,8,6,2,5,4,8,3,7};
        System.out.println(new lc11().maxArea(test));
    }
}
