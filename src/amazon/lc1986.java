package amazon;

/*
这道题目确实很难，是一道周赛的题目2021/09/04
思路想了半天，还是室友点拨才做出来的，参考这个题解：
https://leetcode-cn.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/solution/java-er-fen-tong-di-gui-jian-zhi-0ms100-uen55/
然后从数据量小于14可以看出来这道题目明显是一道暴力求解题
这道题目一开始的二分其实在做的就是先取一个可能的工作时间段mid，也就是说在mid段时间内可以做完所有工作，mid即为答案
这个概念一开始一直没搞懂，其实意思就是说先假设答案为mid段工作时间，然后用暴力回溯去遍历去验证能不能做到。
如果能做到的话，进一步缩小目标，看看更小一点的工作时间段能不能做到。
如果不能做到，那就扩大范围，看看更大一点的时间段能不能做到。

通过二分法来确认范围可以少花很多时间，但是核心还是暴力枚举。先二分假设一个答案可行，用暴力回溯解。
然后还有一个关键就是剪枝，请教了室友以后才知道为什么，有以下几个注意点，注意这里timeNum其实就是可以理解为桶的概念
每一个timeNum的容量就是sessionTime + 1, 加1的原因就是为了对齐下标：
1. 要注意BackTracking函数的定义是在当前tasks[index]下，测试可以放在哪个timeNum里面，也就是说整个这一层的Backtracking
   都是在为一个tasks[index]服务的，那么如果发现在for循环里面第i个timeNum已经有过计算了，那就不必检查了，因为结果是一样的，
   timeNum之间是等价的
2. 上面的解释会牵扯到一个疑惑，那就是为什么之前发生过的就一定重复？妙就妙在他的那个boolean数组是在当前层针对这一个tasks[index]建立的
   换句话说也就是这个boolean其实只为当前tasks[index]服务，如果当前层有这个数了，
   说明如果再把tasks[index]放在这个重复的timeNum里面，则一定会有重复计算。
3. 关于if(visited[curTimeSum]) continue;为什么用continue而不能用break的原因是因为你其实是在遍历timeNum,
   假设在这个tasks[index]在之前的桶有相同值确实需要调出循环，但是还有情况是万一剩下的循环里面有桶的值是和之前的不一样的
   然后能让timeNum[i] + tasks[index]得到一个新值的话，那也是对的
* */

import java.util.Arrays;

public class lc1986 {
    private int[] tasks;
    private int sessionTime;
    public int minSessions(int[] tasks, int sessionTime) {
        this.tasks = tasks;
        this.sessionTime = sessionTime;
        Arrays.sort(tasks);
        int tLen = tasks.length - 1, left = 1, right = tasks.length;
        if(right == 1) return 1;
        while (left < right){
//            System.out.println(left);
//            System.out.println(right);
            int mid = left + (right - left) / 2;
            int[] timeNum = new int[mid + 1];
            if(BackTracking(tLen, mid, timeNum)){
                right = mid;
            }else left = mid + 1;
        }
        return left;
    }

    private boolean BackTracking(int index, int assumeRes, int[] timeNum){
//        System.out.println(index);
        if(index == -1) return true;
        boolean[] visited = new boolean[sessionTime + 1];
        for(int i = 1; i <= assumeRes; i++){
//            我这里和题解的思路有点不一样，但是我觉得应该判断的
//            是当前tasks[index]加上以后是不是重复，而不是原始重复不重复
            int curTimeSum = timeNum[i] + tasks[index];
            if(curTimeSum > sessionTime) continue;
            if(visited[curTimeSum]) continue;
            visited[curTimeSum] = true;
            timeNum[i] += tasks[index];
            if(BackTracking(index - 1, assumeRes, timeNum)) return true;
            timeNum[i] -= tasks[index];
        }
        return false;
    }

    public static void main(String[] args) {
        int[] test = {3,1,3,1,1};
        System.out.println(new lc1986().minSessions(test, 8));
    }
}
