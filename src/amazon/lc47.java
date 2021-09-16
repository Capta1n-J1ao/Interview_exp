package amazon;

/*
lc40和lc47很像，建议在一起联系，体会差别

关于剪枝的问题看这个视频，讲的非常清楚：
https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
* */

import java.util.*;

public class lc47 {
    private List<List<Integer>> res;
    private int numLen;
    private int[] nums;
    private boolean[] visited;
    public List<List<Integer>> permuteUnique(int[] nums) {
        this.res = new LinkedList<>();
        this.nums = nums;
        this.numLen = nums.length;
        this.visited = new boolean[numLen];
        Arrays.sort(nums);
        BackTracking(new LinkedList<>());
        return res;
    }

    private void BackTracking(List<Integer> curRes){
        if(curRes.size() == numLen){
            res.add(new LinkedList<>(curRes));
            return;
        }
        for(int i = 0; i < numLen; i++){
            if(i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) continue;
            if(visited[i]) continue;
            visited[i] = true;
            curRes.add(nums[i]);
//            System.out.println(curRes);
            BackTracking(curRes);
            visited[i] = false;
            curRes.remove(curRes.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] test = {1,1,2};
        System.out.println(new lc47().permuteUnique(test));
    }
}
