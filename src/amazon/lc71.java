package amazon;

/*
这道题目总的来说总结一下就是：
1. 头上要有一个"/"，结尾不能有"/"
2. 见到".."不仅不能加入最终答案，还要要去掉".."前面的一项
3. 见到"." 不能加入最终答案

经过公司的实验，结合资料得到如下结论：
~代表你的/home/用户名目录
假设你的用户名是x，那么~/就是/home/x/
.是代表此目录本身，但是一般可以不写
所以cd ~/. 和cd ~ 和cd ~/效果是一样的
但是.后面有东西又是另外一个问题，点在文件名头部，代表一个隐藏文件，
例如~/.local是你的主目录下一个.local的文件夹的路径，并且从.可以看出，
这是一个隐藏文件，如果不用ls -a的话，一般ls是无法看到的。

题解：
https://leetcode-cn.com/problems/simplify-path/solution/zhan-by-powcai/
* */

import java.util.Deque;
import java.util.LinkedList;

public class lc71 {
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!item.isEmpty() && !item.equals(".")) stack.push(item);
        }
        String res = "";
        for (String d : stack) res = "/" + d + res;
        return res.isEmpty() ? "/" : res;
    }

    public static void main(String[] args) {
        System.out.println(new lc71().simplifyPath("/a//./b/../../c/"));
        System.out.println(new lc71().simplifyPath("/a//./b/./../c/"));
//        System.out.println(new lc71().simplifyPath("/../"));
    }
}
