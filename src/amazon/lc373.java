package amazon;

/*
一次过的，这道题目可以不用写了，常规 PriorityQueue的思路
* */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class lc373 {
    private List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int num1Len = nums1.length;
        int num2Len = nums2.length;
        if(nums1 == null || nums2 == null || num1Len == 0 || num2Len == 0) return res;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] + o2[1] - (o1[0] + o1[1]);
            }
        });

        for(int i = 0; i < num1Len; i++){
            for(int m = 0; m < num2Len; m++){
                if(priorityQueue.size() >= k){
                    int[] topNum = priorityQueue.peek();
                    if(nums1[i] + nums2[m] < topNum[0] + topNum[1]){
                        priorityQueue.poll();
                    }else continue;
                }
                priorityQueue.add(new int[]{nums1[i], nums2[m]});
            }
        }

        int pLen = priorityQueue.size();
        for (int i = 0; i < pLen; i++){
            int[] temp = priorityQueue.poll();
            List<Integer> curRes = new LinkedList<>();
            curRes.add(temp[0]);
            curRes.add(temp[1]);
            res.add(curRes);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test1 = {1,7,11};
        int[] test2 = {2,4,6};
        System.out.println(new lc373().kSmallestPairs(test1, test2,3));
    }
}
