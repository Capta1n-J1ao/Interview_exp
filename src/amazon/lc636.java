package amazon;

/*
这道题目一开始想用HashMap来做，虽然接近，但是解决不了需要调用前后数据的问题
所以应该使用stack来解决。这道题目因为题解不多，所以搞了挺久，如果想不出就尽快看我的代码，已经是整合过的了。
********************************
这道题目还有一个非常重要的题意需要理解！这也是在做完题目以后才搞懂的！
那就是题目中这句话的含义：“函数可能会递归调用或者被其他函数调用。”
也就是比如id = 0先start，然后没有end的时候 id = 1再start，注意这时候是不可能有id = 0先于id = 1 end的
因为是递归调用，一定是id先开始的后结束，后开始的先结束！其实和括号很像！
所以第二个test case里面的情况是不可能发生的！！！所以千万别想多
********************************
这道题目题解不多，而且主要的逻辑难点在于for循环里面的else部分，还是要自己写一下，然后自己想,经过编码，
增加一个类来处理logs真的会让编码简单很多倍：
https://leetcode-cn.com/problems/exclusive-time-of-functions/solution/can-kao-bie-ren-xie-de-hou-chou-xiang-guo-de-java-/

然后if else逻辑参考了如下题解：
https://leetcode-cn.com/problems/exclusive-time-of-functions/solution/zhan-java-by-portal20-sr2h/
* */

import java.util.*;

public class lc636 {
    public class logStr{
        int id;
        int time;
        boolean isStart;
        public logStr(String[] str){
            id = Integer.valueOf(str[0]);
            time = Integer.valueOf(str[2]);
            isStart = str[1].equals("start");
        }
    }
    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<logStr> stack = new ArrayDeque<>();
        int[] res = new int[n];
        for(String str : logs){
            logStr log = new logStr(str.split(":"));
            if(log.isStart){
                if(!stack.isEmpty()){
                    logStr temp = stack.peekFirst();
//                    注意这里的计数差别，不用+1的原因是在于两个logStr都是start，
//                    所以区间为[temp.time, log.time)，log.time时刻算入当前id所在的时间段。
                    res[temp.id] += log.time - temp.time;
                }
                stack.addFirst(log);
            }else {
                logStr tempEnd = stack.pollFirst();
//                    注意这里的计数差别，需要+1的原因是在于两个logStr一个start一个end，
//                    所以区间为[temp.time, log.time]他们是匹配的。
                res[tempEnd.id] += log.time - tempEnd.time + 1;
//                下面这句很重要，结合test case搞清楚,这个其实是因为在上面start的case里面，
//                其实每次都已经结算掉了单线程的时间了，所以这样的话要用这个方法更新时间
                if(!stack.isEmpty()) stack.peekFirst().time = log.time + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        List<String> testLog = new LinkedList<>();
//        testLog.add("0:start:0");
//        testLog.add("1:start:2");
//        testLog.add("1:end:5");
//        testLog.add("2:start:6");
//        testLog.add("2:end:9");
//        testLog.add("0:end:12");
//        System.out.println(Arrays.toString(new lc636().exclusiveTime(3, testLog)));

        List<String> testLog = new LinkedList<>();
        testLog.add("0:start:0");
        testLog.add("1:start:2");
        testLog.add("0:end:5");
        testLog.add("2:start:6");
        testLog.add("2:end:9");
        testLog.add("1:end:12");
        System.out.println(Arrays.toString(new lc636().exclusiveTime(3, testLog)));
    }
}
