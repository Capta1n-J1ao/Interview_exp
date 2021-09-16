package amazon;

/*
lc341
这个iterator很有意思，其实和for循环一样，就是把一个类型里面的数据穷尽一遍，
常见的iterator有三种，一种是iterator可以用于所有Collections类，
另一种是Listiterator只能应用于list，其中iterator和Listiterator的区别参见下面第一个链接。
最后一种是还有characteriterator，他只能用于char或者string里面，
相应的应用场景参见第二个链接，总的来说其实功能一样，只是对于不同类有一些细分，这个也算一个知识点的完善。

iterator和listiterator区别:
https://www.cnblogs.com/yf11/p/6699775.html
characteriterator:
https://blog.csdn.net/exceptional_derek/article/details/16917269

题目给了NestedInteger这种数据结构，然后要我们处理List<NestedInteger>,将其扁平化。
首先List<NestedInteger> 是一个列表，里面元素类型是NestedInteger。
而ListNestedInteger是什么呢，它可能是一个整数，

还有一个题目难点，下面这个题解有说明：
题解这个里面有句话很重要，迭代器的访问方式一般是：调用next()之前，必定先调用hasNext()
https://leetcode-cn.com/problems/flatten-nested-list-iterator/solution/bian-ping-hua-qian-tao-de-lie-biao-die-d-2cw6/

代码的话参考下面这个题解，我觉得方法二真迭代器更加符合题意：
https://leetcode-cn.com/problems/flatten-nested-list-iterator/solution/java-liang-chong-jie-fa-by-wwwhang/
 */


import java.util.*;

public class NestedIterator implements Iterator<Integer> {

    Deque<NestedInteger> stack = new ArrayDeque<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        for(int i = nestedList.size() - 1; i >= 0; i--){
            stack.addFirst(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pollFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        if(stack.isEmpty()) return false;
        NestedInteger  temp = stack.peekFirst();
        if(temp.isInteger()) {
            return true;
        }else {
//            第一种写法，但是对我来说不是很好理解,特别是那个return hasNext
////            这个地方错了，排查挺久
////            List<NestedInteger> tempList = stack.peekFirst().getList();
//            List<NestedInteger> tempList = stack.pollFirst().getList();
//            for(int i = tempList.size() - 1; i >= 0; i--){
////                if(tempList.get(i).isInteger()) stack.addFirst(tempList.get(i));
//                stack.addFirst(tempList.get(i));
////                写错地方了，所以一支部队
////                else return hasNext();
//            }
//            return hasNext();
//            第二种写法，自己写的
            while (!stack.isEmpty() && !stack.peekFirst().isInteger()){
                List<NestedInteger> tempList = stack.pollFirst().getList();
                for(int i = tempList.size() - 1; i >= 0; i--){
                    stack.addFirst(tempList.get(i));
                }
            }
            return !stack.isEmpty();
        }
    }



    public interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
    }
}
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
