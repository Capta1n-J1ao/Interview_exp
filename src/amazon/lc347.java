package amazon;

import java.util.*;

public class lc347 {
    private HashMap<Integer, Integer> hashMap = new HashMap<>();
    public int[] topKFrequent(int[] nums, int k) {
        for(int num : nums){
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return hashMap.get(o1) - hashMap.get(o2);
            }
        });

        for(int num : hashMap.keySet()){
            minHeap.add(num);
            if(minHeap.size() > k) minHeap.poll();
        }

        int[] res = new int[minHeap.size()];
        int index = 0;
        for(int num : minHeap){
            res[index] = num;
            index++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {4,5,1,1,1,2,2,3,3,3};
        System.out.println(Arrays.toString(new lc347().topKFrequent(test, 2)));
    }
}
