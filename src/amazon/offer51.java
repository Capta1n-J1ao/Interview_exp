package amazon;

/*
这道题目是做lc315的前提，题解看liweiwei的视频即可，预留1-2个小时，坑巨多：
https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/shu-zu-zhong-de-ni-xu-dui-by-leetcode-solution/
* */

import java.util.Arrays;

public class offer51 {
    private int[] nums, temp;
    private int res = 0;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        int numLen = nums.length;
        if(numLen < 2) return 0;
        temp = new int[numLen];
        return divide(0, numLen - 1);
    }

//    函数的定义是得到在[left,right]范围内逆序对的个数
    private int divide(int left, int right){
        if(left == right) return 0;
        int mid = left + (right - left) / 2;
//        这个是左半边分治得到的结果
        int leftRes = divide(left, mid);
//        这个是右半边分治得到的结果
        int rightRes = divide(mid + 1, right);
//        这个是把上面左右半边合在一起得到的结果
        int mergeLR = merge(left, mid, right);
        return leftRes + rightRes + mergeLR;
    }
//  下面这个函数最难得点其实是区分nums和temp数组所代表的的意义，这个点非常重要，
//    直接决定了能不能写对后面的判断
//    1. temp其实是对nums的一个复制，相当于进行merge以后temp是原来nums未排序的数组
//    2. 真正的排序在merge的时候已经在nums里面做好了，基准就是用的temp的
//    所以代码怎么写取决于你怎么使用这两个数组
    private int merge(int left, int mid, int right){
        int leftStart = left, rightStart = mid + 1, curRes = 0;
//        注意这里每次merge的时候一定要初始化temp!下面也再次强调了
//        是用temp进行比较！所以temp一定要正确。而且temp不能再主函数里面直接等于nums
//        这个是很关键的，因为temp在递归的时候每次都会变，如果只初始化一次那就一定会出错
//        如果要找到相对位置，每次都要重新对temp赋值才行！
//        找了很久才找到的问题！
        for(int i = left; i <= right; i++) temp[i] = nums[i];
        for(int i = left; i <= right; i++){
//            思考当leftStart和rightStart其中如果有一方全部走完，
//            还剩下另一方有没走完的时候
            if(rightStart > right){
                nums[i] = temp[leftStart];
                leftStart++;
                continue;
            }else if(leftStart > mid){
                nums[i] = temp[rightStart];
                rightStart++;
                continue;
            }
//            下面才是真正比大小的merge
//            记住这里要用temp进行比较，
//            因为对数组的排序都是在temp里面进行的
//            和上面相呼应！一定要每次都初始化temp
//            然后通过比较得到的数据反馈给nums，
//            让nums在之后的递归中再充当基准
            if(temp[leftStart] <= temp[rightStart]){
                nums[i] = temp[leftStart];
                leftStart++;
            }else if(temp[leftStart] > temp[rightStart]){
                nums[i] = temp[rightStart];
                curRes += (mid - leftStart + 1);
//                System.out.println(Arrays.toString
//                        (Arrays.copyOfRange(temp, left, right + 1)) + " " + curRes + " " + leftStart + " " + mid  + " " + rightStart);
                rightStart++;
            }
        }
//        System.out.println(Arrays.toString
//                (Arrays.copyOfRange(temp, left, right + 1)) + " " + curRes + " " + left + " " + mid  + " " + right);
        return curRes;
    }

    public static void main(String[] args) {
        int[] test = {7,5,6,4};
        System.out.println(new offer51().reversePairs(test));
    }
}
