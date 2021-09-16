package amazon;

/*
lc31和lc60可以放在一起做，因为都是有一种排序的感觉，60和46也是类似
但是方法完全不一样  所以把31/46/60放在一起做很有意思
常规liweiwei题解，质量很高，建议两种方法全会，因为各有优势
这里有以下几个注意点：
1. 对于要不要k - 1，我把两种方法都写了，结论是各有各的tricky的地方，总的来说还是推荐第一种
2. 对于需要多次删除的情况，list选用Linkedlist,原因在下面也说明了
https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
* */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class lc60 {
//    liweiwei的解法一，但是这里这个k - 1其实不太友好
//    public String getPermutation(int n, int k) {
//        int[] dp = new int[n + 1];
//        dp[0] = 1;
//        for(int i = 1; i <= n; i++) dp[i] = i * dp[i - 1];
////      经过试验发现linkedlist更快
////        List<Integer> arrayList = new ArrayList<>();
//        List<Integer> linkedList = new LinkedList<>();
//        for(int i = 1; i <= n; i++) linkedList.add(i);
////        这里这个k--是难点，想一下n = 4, k = 6就明白了
//        k--;
//        StringBuilder res = new StringBuilder();
//        for (int i = n - 1; i >= 0; i--){
//            int index = k / dp[i];
//            res.append(linkedList.get(index));
//            linkedList.remove(index);
//            k %= dp[i];
//        }
//        return res.toString();
//    }

//  经过理解以后，写了一种优化的解法，有如下改进：
//  1. 方法可以不用操作k - 1
//  2. 同事要注意liweiwei说的对于List种类的选取，他的理由如下：
//     每次选出一个数，就将这个数从列表里面拿出。这个列表需要支持频繁的删除操作，
//     因此使用双链表。在 Java 中 LinkedList 就是使用双链表实现的。
//    public String getPermutation(int n, int k) {
//        int[] dp = new int[n + 1];
//        dp[0] = 1;
//        List<Integer> linkedList = new LinkedList<>();
//        for(int i = 1; i <= n; i++) {
//            dp[i] = i * dp[i - 1];
//            linkedList.add(i);
//        }
//        StringBuilder res = new StringBuilder();
//        for (int i = n - 1; i >= 0; i--){
//            int index = k / dp[i];
////            如果不用k - 1会需要改两处 一个是加一个判断，一个是对k的处理
//            if(k % dp[i] == 0) index--;
//            res.append(linkedList.get(index));
////            System.out.println(res);
//            linkedList.remove(index);
////            下面这句话要注意，不能这么写，否则就不对
////            想一下n = 3, k = 2的第二步就懂了
////            k %= dp[i];
//            k -= index * dp[i];
//        }
//        return res.toString();
//    }

//    方法二，DFS,其实在时间复杂度来说我觉得应该是低于方法一的，
//    因为他每次都要跑一遍所有没有访问过的候选
    private int n, k;
    private StringBuilder res;
    private boolean[] visited;
    private int[] dp;
    public String getPermutation(int n, int k) {
        this.n = n;
        this.k = k;
        dp = new int[n + 1];
        visited = new boolean[n + 1];
        res = new StringBuilder();
        dp[0] = 1;
        for(int i = 1; i <= n; i++) dp[i] = i * dp[i - 1];
        DFS(0);
        return res.toString();
    }

    private void DFS(int index){
        if(index == n) return;
        int numStart = dp[n - 1 - index];
        for(int i = 1; i <= n; i++){
            if(visited[i]) continue;
//            这里一定要思考清楚<还是<=，本质和那个k - 1思考方式一样
            if(numStart < k) {
                k -= numStart;
                continue;
            }
            res.append(i);
            visited[i] = true;
            DFS(index + 1);
            return;
        }
    }

    public static void main(String[] args) {
//        正确答案： 213
        System.out.println(new lc60().getPermutation(3, 3));
//        正确答案: 132
//        System.out.println(new lc60().getPermutation(3, 2));
    }
}
