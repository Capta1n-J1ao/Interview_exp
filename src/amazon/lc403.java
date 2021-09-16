package amazon;

/*
这个视频虽然思路用的是dp,但是方法更加清晰，更加符合我的思路：
https://www.bilibili.com/video/BV1jt4y1k768?from=search&seid=10851032092063704337

其实就是为了做一个表格：
index :                 0   1   2   3   4   5   6   7

stone pos :             0   1   3   5   6   8   12  17

the steps current       1   0   1   1   0   1   3   4
position can jump ：        1   2   2   1   2   4   5
                            2   3   3   2   3   5   6
                                        3   4
                                        4

在理解了上面的这个以后，其实效率高的dp解法的核心也是这个，可以参考这个题解，但是必须要搞懂第一种，
后面这种其实就使用数组替换hashMap提升效率，如果前面不搞懂，不容易理解：
https://leetcode-cn.com/problems/frog-jump/solution/bfsde-si-xiang-by-gary-79-s72j/
下面这个也可以：
https://leetcode-cn.com/problems/frog-jump/solution/gong-shui-san-xie-yi-ti-duo-jie-jiang-di-74fw/
* */

import java.util.HashMap;
import java.util.HashSet;

public class lc403 {
    public boolean canCross(int[] stones) {
        if(stones[1] != 1) return false;
        int sLen = stones.length;
        HashMap<Integer, HashSet<Integer>> hashMap = new HashMap<>();
        hashMap.put(0, new HashSet<>());
        hashMap.get(0).add(1);
        for(int i = 1; i < sLen; i++){
            hashMap.put(i, new HashSet<>());
            for(int k = 0; k < i; k++){
                int distance = stones[i] - stones[k];
                if(hashMap.get(k).contains(distance)){
//                    下面这两个判断是为了防止越界，但是目前的测试用例看不出明显的区别，不加也没什么差别
                    if(distance - 1 >= 0) hashMap.get(i).add(distance - 1);
                    hashMap.get(i).add(distance);
//                    从位置 k 发起的跳跃最多不超过 k + 1
//                    因为每次跳跃，下标至少增加 1，而步长最多增加 1
                    if(distance + 1 <= sLen)hashMap.get(i).add(distance + 1);
                }
            }
        }
        return !hashMap.get(sLen - 1).isEmpty();
    }

    public static void main(String[] args) {
        int[] test = {0,1,3,5,6,8,12,17};
        System.out.println(new lc403().canCross(test));
    }
}
