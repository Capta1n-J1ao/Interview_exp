package amazon;

/*
这里的难点如下：
1. 构造比较器
2. 怎样让答案按照出现频率次数以及频率次数一样的时候需要比较字母顺序

第二个问题的解答是要使用String里面的compareTo方法来对String进行比较，
这个很关键，之前练习不多，记不住
* */

import java.util.*;

public class lc692 {
    private List<String> res = new LinkedList<>();
    private HashMap<String, Integer> hashMap = new HashMap<>();
    public List<String> topKFrequent(String[] words, int k) {
        for(String word : words){
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> minHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
//                频率次数一样的时候需要比较字母顺序，很关键！
                if( hashMap.get(o1) == hashMap.get(o2)) return o2.compareTo(o1);
                return hashMap.get(o1) - hashMap.get(o2);
            }
        });

        for(String word : hashMap.keySet()){
//            if(minHeap.size() < k) minHeap.add(word);
//            else {
//                String temp = minHeap.poll();
//                if(hashMap.get(temp) < hashMap.get(word) || (hashMap.get(temp) == hashMap.get(word) && temp.compareTo(word) > 0)) minHeap.add(word);
//                else minHeap.add(temp);
//            }
            minHeap.add(word);
            if(minHeap.size() > k) minHeap.poll();
        }

        for(int i = 0; i < k; i++){
            res.add(minHeap.poll());
        }
//        注意要按照出现频率次数排序
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        String[] test = {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(new lc692().topKFrequent(test, 2));


        String[] test1 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        System.out.println(new lc692().topKFrequent(test1, 4));

        String[] test2 = {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(new lc692().topKFrequent(test, 1));
    }
}
