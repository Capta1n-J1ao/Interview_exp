package amazon;

/*
这道题目非常难，推荐两种做法，一种是dp，一种是单调栈，
其中dp这个方法其实个人觉得就是从暴力法优化来的，比较好理解。
但是单调栈是我一开始的思路，一开始想到就是括号那道题目（lc20），
但是这道题目如果用单调栈来做会难爆，有些细节非常难想，
所以可以优先用dp做，然后再用单调栈做。
单调栈看似和栈不一样，其实是一样的，单调栈使用栈手动实现的，
也就是说如果用栈做这道题一定是实现了单调栈才做出来的。

两种方法的时间复杂度都是O（n），但是实际来看的话方法一比方法二更快一点。
两种方法的空间复杂度都是O（n），但是实际来看的话方法二比方法一更小一点。

讲解的话还是liweiwei的最清楚：
https://leetcode-cn.com/problems/trapping-rain-water/solution/bao-li-jie-fa-yi-kong-jian-huan-shi-jian-zhi-zhen-/
* */

import java.util.Stack;

public class lc42 {
//    方法一，基于暴力法优化的dp，个人感觉有点伪dp。
//    public int trap(int[] height) {
//        int hLen = height.length;
//        int res = 0;
//        int[] leftHighest = new int[hLen];
//        int[] rightHighest = new int[hLen];
//        for(int i = 1; i < hLen; i++){
//            leftHighest[i] = Math.max(height[i - 1], leftHighest[i - 1]);
//        }
//        for(int i = hLen - 2; i > 0; i--){
//            rightHighest[i] = Math.max(height[i + 1], rightHighest[i + 1]);
//        }
//
//        for(int i = 1; i < hLen; i++){
//            int temp = Math.min(leftHighest[i], rightHighest[i]);
//            res += temp > height[i] ? (temp - height[i]) : 0;
//        }
//        return res;
//    }

//    方法二使用的是栈，但是这个栈如果要应用在这道题目里面的话要用到一种名为单调栈的结构，
//    相关解释如下链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/dan-diao-zhan-jie-jue-jie-yu-shui-wen-ti-by-sweeti/
//    值得注意的是这道题目用栈的话会很难，我想了很久才想出来，
//    它的主要思路其实就是和括号题思路一样，当后出现的墙比之前的墙短时就入栈；
//    当后出现的墙比之前的墙高时就把栈顶元素压出栈，然后计算压出栈的元素左右的墙能积多少水，
//    相当于其实是随着高度一行一行计算积水
//    public int trap(int[] height) {
//        int hLen = height.length;
//        int index = 0, res = 0;
//        Stack<Integer> stack = new Stack<>();
//        while (index < hLen){
//            while (!stack.isEmpty() && height[index] > height[stack.peek()]){
//                int temp = stack.pop();
//                if(stack.isEmpty()) break;
//                int left = stack.peek();
////                相当于一层一层计算积水
//                int distance = index - left - 1;
//                int vol = Math.min(height[index], height[left]) - height[temp];
//                res += vol * distance;
//            }
//            stack.push(index);
//            index++;
//        }
//        return res;
//    }


//    二刷2021/3/5, dp的解法和lc238很像。
    public int trap(int[] height) {
        int hLen = height.length, res = 0;
        int[] leftHighest = new int[hLen];
        int[] rightHighest = new int[hLen];
        for(int i = 1; i < hLen; i++){
            leftHighest[i] = Math.max(leftHighest[i - 1], height[i - 1]);
        }
        for(int i = hLen - 2; i >= 0; i--){
            rightHighest[i] = Math.max(rightHighest[i + 1], height[i + 1]);
        }

        for(int i = 1; i < hLen - 1; i++){
            int edge = Math.min(leftHighest[i], rightHighest[i]);
            if(height[i] < edge) res += edge - height[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {4,2,0,3,2,5};
        System.out.println(new lc42().trap(test));
    }
}
