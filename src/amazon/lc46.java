package amazon;

/*
这道题目在做lc212和lc60前热手，对我启发很大。
lc31和lc60可以放在一起做，因为都是有一种排序的感觉，60和46也是类似
* */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class lc46 {
//    private List<List<Integer>> res = new LinkedList<>();
//    private List<String> res1 = new LinkedList<>();
//    private int[] nums;
//    private int numLen;
//    private boolean[] visited;
//    public List<List<Integer>> permute(int[] nums) {
//        this.nums = nums;
//        numLen = nums.length;
//        visited = new boolean[numLen];
//        if(numLen == 0) return res;
//        BackTracking(0, new ArrayList<>());
//        return res;
//    }
//
//    private void BackTracking(int index, List<Integer> curRes){
//        if(curRes.size() == numLen){
////            注意这个不可以初始化，要改成ArrayList或者LinkedList
////            res.add(new List<Integer>(curRes));
//            res.add(new LinkedList<>(curRes));
//            return;
//        }
//        for(int i = 0; i < numLen; i++){
//            if(!visited[i]){
//                visited[i] = true;
//                curRes.add(nums[i]);
//                BackTracking(index + 1, curRes);
//                //关键步骤
//                curRes.remove(curRes.size() - 1);
//                visited[i] = false;
//            }
//
//        }
//    }
//
//
//    private void Back1(Codec.TreeNode node, LinkedList<String> curRes){
//        if(node.left == null && node.right == null){
//            res1.add(String.join("1", curRes));
//        }
//    }



//    三刷， 2021/5/2
//    private List<List<Integer>> res = new LinkedList<>();
//    private List<String> res1 = new LinkedList<>();
//    private int[] nums;
//    private int numLen;
//    private boolean[] visited;
//    public List<List<Integer>> permute(int[] nums) {
//        this.nums = nums;
//        numLen = nums.length;
//        if(nums == null || numLen == 0) return res;
//        visited = new boolean[numLen];
//        BackTracking(new LinkedList<>(), 0);
//        return res;
//    }
//
//    private void BackTracking(List<Integer> curRes, int count){
//        if(count == numLen){
//            res.add(new LinkedList<>(curRes));
//            return;
//        }
//        for(int i = 0; i < numLen; i++){
//            if(!visited[i]){
//                curRes.add(nums[i]);
//                visited[i] = true;
//                BackTracking(curRes, count + 1);
//                curRes.remove(curRes.size() - 1);
//                visited[i] = false;
//            }
//        }
//    }


//    四刷,2021/06/23
    private List<List<Integer>> res = new LinkedList<>();
    private List<String> res1 = new LinkedList<>();
    private int[] nums;
    private int numLen;
    private boolean[] visited;
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        numLen = nums.length;
        if(nums == null || numLen == 0) return res;
        visited = new boolean[numLen];
        BackTracking(new LinkedList<>(), 0);
        return res;
    }

    private void BackTracking(List<Integer> curRes, int count){
        if(count == numLen){
            res.add(new LinkedList<>(curRes));
            return;
        }
        for(int i = 0; i < numLen; i++){
            if(!visited[i]){
                curRes.add(nums[i]);
                visited[i] = true;
                BackTracking(curRes, count + 1);
                curRes.remove(curRes.size() - 1);
                visited[i] = false;
            }
        }
    }
}
