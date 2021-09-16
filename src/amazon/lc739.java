package amazon;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class lc739 {
    public int[] dailyTemperatures(int[] T) {
        int tLen = T.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[tLen];
        if(tLen == 0) return res;
        stack.addFirst(0);
        for(int i = 1; i < tLen; i++){
            while (!stack.isEmpty()){
                int preIndex = stack.peekFirst();
                if(T[i] > T[preIndex]){
                    res[preIndex] = i - preIndex;
                    stack.pollFirst();
                }else break;
            }
            stack.addFirst(i);
        }
//        下面这个不要也不要紧，因为res默认没有操作的话里面就是0
//        while (!stack.isEmpty()){
//            res[stack.pollFirst()] = 0;
//        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] testRes = new lc739().dailyTemperatures(test);
        System.out.println(Arrays.toString(testRes));
    }
}
