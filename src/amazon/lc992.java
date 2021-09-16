package amazon;

/*
一开始想用正向思维，就是用双指针去卡正好出现K个不同数的情况，但是实际写下来发现根本卡不住的，
之后的二刷三刷也可以仔细想一下这个问题 ，其实是不好对case进行判断的
所以这里就要借用liweiwei的思路来计算，看一下视频就全清楚了，
是一种很巧妙的办法，这个可能需要硬记，而且这个方法效率最高
https://leetcode-cn.com/problems/subarrays-with-k-different-integers/solution/k-ge-bu-tong-zheng-shu-de-zi-shu-zu-by-l-ud34/

题解里面很重要的一点就是：
这里把 「恰好」 转换成为 「最多」须要一点求解「双指针（滑动窗口）」问题的经验。
建立在熟练掌握这一类问题求解思路的基础上
也就是说使用双指针解决的问题的问法基本都会出现「最小」、「最大」这样的字眼。
相关题目是76/209/159/424
* */

import java.util.HashMap;

public class lc992 {
//    private HashMap<Integer, Integer> hashMap;
//    public int subarraysWithKDistinct(int[] A, int K) {
//        int aLen = A.length, left = 0, right = 0, res = 0;
//        hashMap = new HashMap<>();
//        while (right < aLen){
//            if(hashMap.size() < K) {
//                hashMap.put(A[right], hashMap.getOrDefault(A[right], 0) + 1);
//                right++;
//            }else {
//                while (hashMap.size() >= K){
//                    int temp = hashMap.get(A[left]);
//                    if(temp - 1 == 0) hashMap.remove(A[left]);
//                    else {
//                        hashMap.put(A[left], temp - 1);
//                    }
//                    res++;
//                    left++;
//                }
//            }
//        }
//        return res;
//    }

//    private int[] A;
//    private int[] count;
//    private int aLen;
//    public int subarraysWithKDistinct(int[] A, int K) {
//        this.A = A;
//        this.aLen = A.length;
////        这里要注意题目给出了1 <= A[i] <= A.length这个条件，才能用数组，否则就要用hashMap
//
//        int resK = helper(K);
//        int resKminus = helper(K - 1);
//        return resK - resKminus;
//    }
//
//    private int helper(int K){
//        int left = 0, right = 0, res = 0, diffNum = 0;
//        count = new int[aLen + 1];
//        while (right < aLen){
//            if(count[A[right]] == 0) diffNum++;
//            count[A[right]]++;
//            right++;
////            很关键，一定是>而不是>=
////            while (diffNum >= K){
//            while (diffNum > K){
//                if(count[A[left]] == 1) diffNum--;
//                count[A[left]]--;
//                left++;
//            }
//            res += right - left;
//        }
//        return res;
//    }

//    二刷,2021/5/15,注意双指针（滑动窗口）类题目主要是求解最多或者最少等带“最”字的问题
//    因为双指针核心是要固定某一个边界的，不能做到灵活通过边界去判断，具体理解还是要看liweiwei题解
    private int[] nums;
    private int numLen;
    public int subarraysWithKDistinct(int[] nums, int k) {
        this.nums = nums;
        numLen = nums.length;
        return helper(k) - helper(k - 1);
    }

    private int helper(int maxCount){
        int[] cur = new int[numLen + 1];
        int res = 0, count = 0, left = 0, right = 0;
        while (right < numLen){
//            注意这里不能用while去套，想了很久猜想明白，而是应该一步一步去加
//            while (count <= maxCount && right < numLen){
//                if (cur[nums[right]] == 0){
//                    count++;
//                    if(count > maxCount) break;
//                }
//                cur[nums[right]]++;
//                right++;
//            }
            if (cur[nums[right]] == 0){
                count++;
            }
            cur[nums[right]]++;
            right++;
            while (count > maxCount){
                cur[nums[left]]--;
                if(cur[nums[left]] == 0){
                    count--;
                }
                left++;
            }
//            这句话是核心，要好好挺liweiwei怎么讲解这句话的
//            只要count <= k 那么结果就要被加进去
            res += right - left;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] test = {1,2,1,2,3};
        System.out.println(new lc992().subarraysWithKDistinct(test, 2));
    }
}
