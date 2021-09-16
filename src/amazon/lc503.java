package amazon;



import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class lc503 {
//    public int[] nextGreaterElements(int[] nums) {
//        int numLen = nums.length;
//        int[] res = new int[numLen];
//        if(nums == null || numLen == 0) return res;
//        Deque<Integer> stack = new ArrayDeque<>();
//        Arrays.fill(res, -1);
//        for(int i = 0; i < 2 * numLen - 1; i++){
//            while (!stack.isEmpty() && (nums[i % numLen] > nums[stack.peekFirst()])){
//                int temp = stack.pollFirst();
//                res[temp % numLen] = nums[i % numLen];
//            }
//            stack.addFirst(i % numLen);
//        }
//        return res;
//    }

//    二刷，2021/4/28
    public int[] nextGreaterElements(int[] nums) {
        int numLen = nums.length;
        int[] res = new int[numLen];
        if(nums == null || numLen == 0) return res;
        Deque<Integer> stack = new ArrayDeque<>();
        Arrays.fill(res, -1);
        for(int i = 0; i < numLen * 2 - 1; i++){
            while (!stack.isEmpty() && nums[i % numLen] > nums[stack.peekFirst()]){
                int temp = stack.pollFirst();
                res[temp] = nums[i % numLen];
            }
            stack.addFirst(i % numLen);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {1,2,1};
        System.out.println(Arrays.toString(new lc503().nextGreaterElements(test)));
    }
}
