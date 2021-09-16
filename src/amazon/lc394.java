package amazon;

import java.util.ArrayDeque;
import java.util.Deque;

/*
在做题目的时候要想到第二个test怎么解决，这也是为什么要实时修改res的原因！
这道题目最好用两个栈，一个是String栈另一个是数字栈，之前有一个道题目也是用这个思路，到时候可以总结下
要注意这道题目不能像常规的那种括号题一样一个一个去出栈，而是应该把一对"[]"作为一个整体入栈，
而这也是这道题目比较难想的一个地方
这道题目千万不要小看，有很多tricky的地方，找了个视频说的非常清晰，按照他的思路在debug了两个小时以后终于做出来
所以做这道题的时候一定要理清思路！
https://www.bilibili.com/video/BV1N4411u7ma?from=search&seid=6697247331960420558
* */

public class lc394 {
//    public String decodeString(String s) {
//        int sLen = s.length(), count = 0;
//        if(sLen == 0) return "";
//        char[] sChar = s.toCharArray();
//        Deque<String> strStack = new ArrayDeque<>();
//        Deque<Integer> numStack = new ArrayDeque<>();
//        StringBuilder res = new StringBuilder();
//        for(int i = 0; i < sLen; i++){
//            char curChar = sChar[i];
//            if('0' <= curChar && curChar <= '9'){
//                count = 10 * count + (curChar - '0');
//            }else if(curChar == '['){
//                numStack.addFirst(count);
//                strStack.addFirst(res.toString());
//                res = new StringBuilder();
//                count = 0;
//            }else if(curChar == ']'){
//                int repeat = numStack.pollFirst();
////                这里关于curRes纠结了很久，一开始一直认为用curRes去存前面遇到的字符串，
////                然后碰到'['就进入strStack,
////                但是这是不对的，结合第一个test case去理解，
////                如果到了最后，ef是不用进strStack的，用curRes就会出错，只有实时更新res才可以
//                StringBuilder curRes = new StringBuilder(strStack.pollFirst());
//                for(int k = 0; k < repeat; k++) {
//                    curRes.append(res);
//                }
//                res = curRes;
//            }else res.append(curChar);
//        }
//        return res.toString();
//    }


//    这个是在完全搞懂以后一定要加入curRes到全局变量的结果，但是在运行到一些case的时候会有问题，
//    例如"leetcode"这种没有[]对的情况
//    所以事实证明不能引入一个curRes,这样多此一举
//    当然，可以把最后一句改成return curRes.toStirng,但这样和res没有区别
    public String decodeString(String s) {
        int sLen = s.length(), count = 0;
        if(sLen == 0) return "";
        char[] sChar = s.toCharArray();
        Deque<String> strStack = new ArrayDeque<>();
        Deque<Integer> numStack = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        StringBuilder curRes = new StringBuilder();
        for(int i = 0; i < sLen; i++){
            char curChar = sChar[i];
            if('0' <= curChar && curChar <= '9'){
                count = 10 * count + (curChar - '0');
            }else if(curChar == '['){
                numStack.addFirst(count);
                strStack.addFirst(curRes.toString());
                curRes = new StringBuilder();
                count = 0;
            }else if(curChar == ']'){
                int repeat = numStack.pollFirst();
                StringBuilder temp = new StringBuilder(strStack.pollFirst());
                for(int k = 0; k < repeat; k++) {
                    temp.append(curRes);
                }
                res = temp;
                curRes = temp;
            }else curRes.append(curChar);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new lc394().decodeString("2[ab2[cd]]ef"));
//        System.out.println(new lc394().decodeString("abc3[cd]xyz"));
//        System.out.println(new lc394().decodeString("3[a]2[bc]"));
    }
}
