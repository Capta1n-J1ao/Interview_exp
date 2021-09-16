package amazon;

/*
思路确实挺巧妙，题解,其实用这个办法出现N次其实都能算出来：
https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/solution/442li-yong-chu-li-by-strange-williamsonv-7cey/

但是看了下有Liweiwei的题解，448/442/41其实都是一种思想，那就是原地哈希
这三道题目都是原地哈希的方法，从左往右难度依次上升，但是思想是一样的，所以放在一起服用
所以用原地哈西再解一遍，代码：
https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/solution/chou-ti-yuan-li-ji-yu-yi-huo-yun-suan-jiao-huan-li/
* */

import java.util.LinkedList;
import java.util.List;

public class lc442 {
//    private List<Integer> res = new LinkedList<>();
//    public List<Integer> findDuplicates(int[] nums) {
//        if(nums == null || nums.length < 2) return res;
//        int numLen = nums.length;
//        for(int i = 0; i < numLen; i++){
//            int index = (nums[i] - 1) % numLen;
////            System.out.println("i = " + i + "   index = " + index);
//            nums[index] = nums[index] + numLen;
//        }
//        for(int i = 0; i <numLen; i++){
////            注意这里的判断一定得是nums[i] - 1，否则会有错
//            if(((nums[i] - 1) / numLen) > 1) res.add(i + 1);
//        }
//        return res;
//    }

//    方法二：原地哈希
    private List<Integer> res = new LinkedList<>();
    private int[] nums;
    public List<Integer> findDuplicates(int[] nums) {
        this.nums = nums;
        if(nums == null || nums.length < 2) return res;
        int numLen = nums.length;
        for(int i = 0; i < numLen; i++){
//            注意下面这两句话的差别，就是这个地方高了挺久,
//            lc41也是这个地方出问题,这是一个很巧妙判断
//            while (nums[i] != i + 1){
            while (nums[nums[i] - 1] != nums[i]){
                swap(nums[i] - 1, i);
            }
        }
        for(int i = 0; i < numLen; i++){
            if(nums[i] - 1 != i) res.add(nums[i]);
        }
        return res;
    }
    private void swap(int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] test = {4,3,2,7,8,2,3,1};
        System.out.println(new lc442().findDuplicates(test));

//        int[] test = {2,1};
//        System.out.println(new lc442().findDuplicates(test));
    }
}
