package amazon;

/*
lc40和lc47很像，建议在一起联系，体会差别

这道题目一开始自己做，想的方法就是用BackTracking，但是有个问题就是会有重复值
于是想了个之前用过的把重复的答案变成String然后放到hashset里面，这样来达到去重的效果
但是感觉方法有点繁琐,在172个例子会超时
同样是Liweiwei的题解：
https://leetcode-cn.com/problems/combination-sum-ii/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/
* */

import java.util.*;

public class lc40 {
//    方法一，超时
//    private int[] candidates;
//    private int target, cLen;
//    private List<List<Integer>> repeatRes, res;
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        this.candidates = candidates;
//        this.target = target;
//        this.res = new ArrayList<>();
//        this.repeatRes = new ArrayList<>();
//        this.cLen = candidates.length;
//        Arrays.sort(candidates);
////        我这个写法直接导致剪枝方法无效，所以仅仅需要一个小修改即可，
////        就是把这个for循环放到BackTracking函数中
//        for(int i = 0; i < cLen; i++){
//            if(candidates[i] > target) break;
//            if(i > 0 && candidates[i] == candidates[i - 1]) continue;
//            BackTracking(i, new LinkedList<>(), target);
//        }
//        HashSet<String> hashSet = new HashSet<>();
//        for(List<Integer> curRes : repeatRes){
//            StringBuilder sb = new StringBuilder();
//            for(Integer num : curRes){
//                sb.append(num).append(',');
//            }
//            hashSet.add(sb.toString());
//        }
////        System.out.println(hashSet);
//        for(String str : hashSet){
//            String[] strings = str.split(",");
//            List<Integer> list = new LinkedList<>();
//            for(String s : strings){
//                list.add(Integer.valueOf(s));
//            }
//            res.add(list);
//        }
//        return res;
//    }
//
//    private void BackTracking(int index, List<Integer> curRes, int target){
//        if(target == candidates[index]){
//            List<Integer> temp = new LinkedList<>(curRes);
//            temp.add(candidates[index]);
//            repeatRes.add(temp);
//            return;
//        }
//        for(int i = index + 1; i < cLen; i++){
//            if(candidates[index] > target) break;
//            curRes.add(candidates[index]);
////            System.out.println(curRes);
////            System.out.println(target);
////            System.out.println(i);
//            BackTracking(i, curRes, target - candidates[index]);
//            curRes.remove(curRes.size() - 1);
//        }
//    }

//    方法二：优化
    private int[] candidates;
    private int target, cLen;
    private List<List<Integer>> res;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        this.res = new ArrayList<>();
        this.cLen = candidates.length;
        Arrays.sort(candidates);
        BackTracking(0, new LinkedList<>(), target);
        return res;
    }

    private void BackTracking(int index, List<Integer> curRes, int target){
        if(target == 0){
            res.add(new LinkedList<>(curRes));
            return;
        }
//        相对于方法一，主要修改都在这里
        for(int i = index; i < cLen; i++){
            if(candidates[i] > target) break;
            if(i > index && candidates[i] == candidates[i - 1]) continue;
            curRes.add(candidates[i]);
//            System.out.println(curRes);
//            System.out.println(target);
//            System.out.println(i);
            BackTracking(i + 1, curRes, target - candidates[i]);
            curRes.remove(curRes.size() - 1);
        }
    }

    public static void main(String[] args) {
//        int[] test = {10,1,2,7,6,1,5};
//        System.out.println(new lc40().combinationSum2(test, 8));

//        int[] test = {2,5,2,1,2};
//        System.out.println(new lc40().combinationSum2(test, 5));

//        int[] test = {3,1,3,5,1,1};
//        System.out.println(new lc40().combinationSum2(test, 8));

        int[] test = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        System.out.println(new lc40().combinationSum2(test, 30));
    }
}
