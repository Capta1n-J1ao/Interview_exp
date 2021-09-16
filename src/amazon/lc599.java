package amazon;

/*
注意看清这道题目的要求：
你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。如果索引相等情况下有多个餐厅，就全部列出

其实思路和twoSum巨像，题解就参考官解即可
* */

import java.util.*;

public class lc599 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        List<String> listRes = new LinkedList<>();
        int minSum = Integer.MAX_VALUE, l1Len = list1.length, l2Len = list2.length;
        for(int i = 0; i < l1Len; i++) hashMap.put(list1[i], i);
        for(int i = 0; i < l2Len; i++){
            String str = list2[i];
            if(hashMap.containsKey(str)){
                int curIndexSum = hashMap.get(str) + i;
                if(curIndexSum < minSum){
                    listRes.clear();
                    listRes.add(str);
                    minSum = curIndexSum;
                }else if(curIndexSum == minSum) listRes.add(str);
            }
        }
//        这个写法很新颖，第一次尝试，就是把list变为Array
        return listRes.toArray(new String[listRes.size()]);
    }
}
