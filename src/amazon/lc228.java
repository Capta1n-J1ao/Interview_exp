package amazon;

import java.util.LinkedList;
import java.util.List;

public class lc228 {
    private List<String> res = new LinkedList<>();
    private int[] nums;
    public List<String> summaryRanges(int[] nums) {
        this.nums = nums;
        if(nums == null || nums.length == 0) return res;
        int numLen = nums.length;
        if(numLen == 1) {
            res.add(String.valueOf(nums[0]));
            return res;
        }
        int start = 0;
        for(int i = 1; i < numLen; i++){
            if(nums[i] - nums[i - 1] != 1) {
                helper(start, i - 1);
                start = i;
            }
        }
        helper(start, numLen - 1);
        return res;
    }

    private void helper(int left, int right){
        if(left == right) {
            res.add(String.valueOf(nums[left]));
        }else {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(nums[left])).append("->").append(String.valueOf(nums[right]));
            res.add(sb.toString());
        }
        return;
    }

    public static void main(String[] args) {
        int[] test = {0,1,2,4,5,7};
        System.out.println(new lc228().summaryRanges(test));

//        int[] test = {0,1,2,4,5,7,8};
//        System.out.println(new lc228().summaryRanges(test));
    }
}
