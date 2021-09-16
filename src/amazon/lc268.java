package amazon;

public class lc268 {
    public int missingNumber(int[] nums) {
        int numLen = nums.length;
        int res = 0;
        for(int i = 0; i < numLen; i++){
            res ^= (nums[i] ^ i);
        }
        return res ^ numLen;
    }

    public static void main(String[] args) {
//        int[] test = {0,1};
        int[] test = {3,0,1};
        System.out.println(new lc268().missingNumber(test));
    }
}
