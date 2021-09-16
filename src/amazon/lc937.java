package amazon;

/*
这道题目其实题目说的很不清楚，重新解释：
给定一串日志，例如：['a1 9 2 3 1', 'g1 act car']；
【定义】a1 9 2 3 1 这类，除了开头标志 a1 外，仅由数字组成的，叫 **数字日志**；
【定义】a1 act car 这类，除了开头标志 al 外，仅由小写字母组成的，叫 **字母日志**；
【排序规则】将日志重新排序，使得所有 **字母日志** 都排在 **数字日志** 之前；
【排序规则】**字母日志** 在内容不同时，忽略标识符后，按内容字母顺序排序；******在内容相同时，按标识符排序；*****
【排序规则】**数字日志** 按原来的顺序排列；


其实这道题目的核心在考察对String处理命令是否熟练。思路不难，主要看对于命令的掌握,且难度颇高。
* */

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class lc937 {
    private LinkedList<String> strList = new LinkedList<>();
    private LinkedList<String> numList = new LinkedList<>();
    public String[] reorderLogFiles(String[] logs) {
        for(String log : logs){
            char isChar = log.charAt(log.length() - 1);
            if(isChar >= 'a' && isChar <= 'z'){
                strList.add(log);
            }else numList.add(log);
        }

//        接下来考察对于String的处理命令是否熟练
//        1. indexOf
//        2. sort函数的重载,要注意对于不同的结构，调用不同的sort！！！不能使用Arrays.sort
//        3. String的比较方法
//        4. 两个List结合使用.addAll
//        5. List结果转为Sring[]
        strList.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int s1Flg = s1.indexOf(' ');
                int s2Flg = s2.indexOf(' ');
                String s1Head = s1.substring(0, s1Flg);
                String s2Head = s2.substring(0, s2Flg);
                String s1Content = s1.substring(s1Flg + 1);
                String s2Content = s2.substring(s2Flg + 1);
                if(s1Content.equals(s2Content)) return s1Head.compareTo(s2Head);
                return s1Content.compareTo(s2Content);
            }
        });

        strList.addAll(numList);
//        下面这个命令看下java手册，其实说的很清楚
        return strList.toArray(new String[strList.size()]);
    }
}
