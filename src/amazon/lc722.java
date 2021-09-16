package amazon;
/*
最好先做lc929再做lc722
自己尝试写了下，其实有两种方法都挺好，但是选择了一种更加适合我思维的方式：
https://leetcode-cn.com/problems/remove-comments/solution/java-yi-dong-jie-fa-by-lu-yang-shan-yu/

下面这个方式很巧妙，并且复习了indexOf的用法：
https://leetcode-cn.com/problems/remove-comments/solution/java-1ms-100-on-o1-by-kuai-lai-yi-ge-da-offer/

这道题目复习了以下几点：
1. 第一个题解复习了split
2. 第二个题解复习了indexOf
* */

import java.util.LinkedList;
import java.util.List;

public class lc722 {
    public List<String> removeComments(String[] source) {
        List<String> res = new LinkedList<>();
        StringBuilder buffer = new StringBuilder();
        for(String s : source){
            buffer.append(s).append("\n");
        }
        int index = 0, bLen = buffer.length();
        StringBuilder curRes = new StringBuilder();
        while (index < bLen - 1){
            char firstChar = buffer.charAt(index);
            char secondChar = buffer.charAt(index + 1);
//            针对行注释
            if(firstChar == '/' && secondChar == '/'){
                while (index < buffer.length() && buffer.charAt(index) != '\n') index++;
            }else if(firstChar == '/' && secondChar == '*'){
//                针对块注释
                index = index + 2;
                while (index < bLen - 1 && !(buffer.charAt(index) == '*' && buffer.charAt(index + 1) == '/')) index++;
                index = index + 2;
            }else {
                curRes.append(buffer.charAt(index));
                index++;
            }
        }
        String[] tempRes = curRes.toString().split("\n");
//        注意这里由于是可能出现\n\n的情况，所以是下面这个而不是上面这个
//        for(String s : tempRes) if(!s.equals(" ")) res.add(s);
        for(String s : tempRes) if(!s.equals("")) res.add(s);
        return res;
    }
}
