package amazon;

/*
这道题目想做出来很容易，但是这样没什么提高
一种进阶的问题就是如果之后想要3的倍数变为"damon"，5的倍数变为"ctj"的话怎么优化
这个才是比较有价值的问题，需要提高代码的可修改性，于是引入一个映射的概念，具体可以看题解：
https://leetcode-cn.com/problems/fizz-buzz/solution/fizz-buzz-by-leetcode/
* */

import java.util.*;

public class lc412 {
    public List<String> fizzBuzz(int n) {
        List<String> res = new LinkedList<>();
//        Map<Integer, String> hashMap = new HashMap<>();
//        用TreeMap的好处就是可以不用在乎放入Map的顺序
        Map<Integer, String> hashMap = new TreeMap<>();
        hashMap.put(5, "Buzz");
        hashMap.put(3, "Fizz");
//        hashMap.put(5, "Buzz");

        for(int i = 1; i <= n; i++){
            StringBuilder curRes = new StringBuilder();
            for(Integer key : hashMap.keySet()){
                if(i % key == 0) curRes.append(hashMap.get(key));
            }
            if(curRes.length() == 0) curRes.append(Integer.toString(i));
            res.add(curRes.toString());
        }
        return res;
    }
}
