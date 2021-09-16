package amazon;

/*
用回溯方法是没有疑问的，主要就是调试
* */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class lc78 {
    private List<List<Integer>> res;
    private int[] nums;
    private int numLen;
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        res = new ArrayList<>();
        numLen = nums.length;
        for(int i = 0; i <= numLen; i++){
            BackTracking(0, i, new ArrayList<>());
        }
        return res;
    }

    private void BackTracking(int index, int subSetLen, List<Integer> curRes){
        if(curRes.size() == subSetLen){
//            System.out.println(new ArrayList<>(curRes));
            res.add(new ArrayList<>(curRes));
            return;
        }
        for(int i = index; i <numLen; i++){
            curRes.add(nums[i]);
            BackTracking(i + 1, subSetLen, curRes);
            curRes.remove(curRes.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] test = {1, 2, 3};
        List<List<Integer>> testRes = new lc78().subsets(test);

    }
}
