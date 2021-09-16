package amazon;

/*
这道题目据说是谷歌面试题，最好会二分法的解法。

liweiwei的题解最清晰：
https://leetcode-cn.com/problems/find-k-closest-elements/solution/pai-chu-fa-shuang-zhi-zhen-er-fen-fa-python-dai-ma/
* */

import java.util.LinkedList;
import java.util.List;

public class lc658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int aLen = arr.length;
        int left = 0;
        int right = aLen - k;
        while (left < right){
            int mid = left + (right - left) / 2;
//            下面这句话一定要这么写，不能写成绝对值，否则对于以下test case会有错误，这么写的原因有如下几点：
//            1. 对于二分的范围取得是arr[mid] ~ arr[mid + k]一共k + 1个数，
//               实际上比较的是两个长度为 k 的区间的「左边界」谁更合适。这两个区间的「并集」的长度是 k + 1
//            2. 虽然取了 k + 1个数，但是根据liweiwei的分析，
//               下面的二分法会自动去掉多余的左边界或者右边界的错误数，
//               具体原因其实就是他在题解里面说的分类讨论，
//               其实就是二分二从k+1个数里面筛选k个数在一个步骤里面就搞定了
//            3. 不用绝对值的原因就是如下test case，但是还能用另一种写法，
//               就是把test case当做特殊情况单独处理,这样就不用搞了，我就是基于这种方法做的
            if(arr[mid] == arr[mid + k]){
                if(x > arr[mid]) left = mid + 1;
                else right = mid;
            }else {
                if(Math.abs(x - arr[mid]) > Math.abs(x - arr[mid + k]) ){
                    left = mid + 1;
                }else {
                    right = mid;
                }
            }
        }

        List<Integer> res = new LinkedList<>();
        for(int i = left; i < left + k; i++){
            res.add(arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {1,1,2,2,2,2,2,3,3};
        System.out.println(new lc658().findClosestElements(test, 3, 3));
    }
}
