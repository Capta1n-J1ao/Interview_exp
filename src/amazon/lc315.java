package amazon;

/*
做这道题目之前一定要先做剑指offer51题，java文件名为offer51，链接如下:
https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/

这道题目应该归在“分治”的主题下面，分治的本质其实就是递归，
这道题目有个特定的解法叫做归并，见到求逆序对或者类似的题目就要套用此方法，这是固定套路，

此题目的官解是liweiwei的，先看下这道题的具体思路：
https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/shu-zu-zhong-de-ni-xu-dui-by-leetcode-solution/
做完这道题目再来理解lc315的话就会简单很多，同样也是liweiwei的题解：
https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/gui-bing-pai-xu-suo-yin-shu-zu-python-dai-ma-java-/
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class lc315 {
//    这里finRes其实操作起来不方便，还是要用数组操作起来才方便
    private List<Integer> finRes = new ArrayList<>();
    private int[] nums, res, tempIndex, index;
    private int count = 1;
    public List<Integer> countSmaller(int[] nums) {
        this.nums = nums;
        int numLen = nums.length;
        if(numLen < 1) return finRes;
        res = new int[numLen];
        index = new int[numLen];
        tempIndex = new int[numLen];
        for(int i = 0; i < numLen; i++) index[i] = i;
        divide(0, numLen - 1);
        for(int i = 0; i < numLen; i++){
            finRes.add(res[i]);
        }
        return finRes;
    }

    public void divide(int left, int right){
        if(left == right) return;
        int mid = left + (right - left) / 2;
        divide(left, mid);
        divide(mid + 1, right);
//        类似于剪枝优化
        if(nums[index[mid]] <= nums[index[mid + 1]]) return;;
        merge(left, mid, right);
    }
/*
    这里还是和offer51一样讲解下index和tempIndex的区别，这个点非常重要，
    直接决定了能不能写对后面的判断
    1. tempIndex相当于是对未排序时候index的复制，在merge这个过程的时候，
       tempIndex其实是不变的
    2. index 在merge的过程中其实就是基于未排列顺序的tempIndex来得到经过
       排序的index数组
* */
    private void merge(int left, int mid, int right){
        int leftStart = left, rightStart = mid + 1;
        for (int i = left; i <= right; i++) tempIndex[i] = index[i];
        for(int i = left; i <= right; i++){
            if(leftStart > mid){
                index[i] = tempIndex[rightStart];
                rightStart++;
                continue;
            }else if(rightStart > right){
                index[i] = tempIndex[leftStart];
//                注意这里是right而不是rightStart,很隐蔽
//                或者写成下面这样也可以
//                res[index[i]] += (rightStart - 1 - mid);
                res[index[i]] += (right - mid);
                leftStart++;
                continue;
            }
//            1. 下面这段写法liweiwei的不太容易懂，所以自己写了个更加容易懂的
//            这样和offer51的逻辑也前后一致
//            2. 比较的都是tempIndex里面的，而不是index里面的，debug很久
            if(nums[tempIndex[leftStart]] <= nums[tempIndex[rightStart]]){
                index[i] = tempIndex[leftStart];
                leftStart++;
                res[index[i]] += (rightStart - mid - 1);
            }else {
                index[i] = tempIndex[rightStart];
                rightStart++;
            }
        }
        System.out.println(count);
        count++;
        System.out.println("index = " + Arrays.toString(index));
        System.out.println("tempIndex = " + Arrays.toString(tempIndex));
        System.out.println("res= " + Arrays.toString(res));
    }

    public static void main(String[] args) {
        int[] test = {5,2,6,1};
        System.out.println(new lc315().countSmaller(test));
    }
}
