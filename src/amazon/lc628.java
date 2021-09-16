package amazon;

/*
仔细分析一下就会知道只有三种情况，在已经把数组排序的情况下：
1. 三正：      最后三位的乘积
2. 两负一正：   nums[0] * nums[1] * nums[numLen - 1]
3. 三负：      这种情况只有一种可能性，那就是全是负数，否则不可能，同样也是最后三位乘积
* */

import java.util.Arrays;

public class lc628 {
    public int maximumProduct(int[] nums) {
        int numLen = nums.length;
        if(numLen < 3) return 0;
        Arrays.sort(nums);
        int res1 = Integer.MIN_VALUE, res2 = Integer.MIN_VALUE;
        if(nums[0] < 0 && nums[1] < 0){
            res1 = nums[0] * nums[1] * nums[numLen - 1];
        }
        res2 = nums[numLen - 1] * nums[numLen - 2] * nums[numLen - 3];
        return Math.max(res1, res2);
    }
}
