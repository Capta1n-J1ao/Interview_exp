package amazon;

/*
这道题目我已开始用的是HashMap，但是记得遇到这种只有单词甚至是本题只有小写单词的时候，
一定要想到可以用数组代替！这样可以省很多时间

这道题这次用的方法性能上来说比之前用的方法要略好一点，但是坑比较多，调试起来没有for循环来的方便，谨慎使用
* */

import java.util.LinkedList;
import java.util.List;

public class lc763 {
    public List<Integer> partitionLabels(String S) {
        int sLen = S.length();
//        这句话是精髓，核心，整个代码的性能提升就靠这个
        int[] end = new int[26];
        char[] wordChar = S.toCharArray();
        for(int i = 0; i < sLen; i++){
            end[wordChar[i] - 'a'] = i;
        }

        int start = 0, count = 0;
        List<Integer> res = new LinkedList<>();
        while (start < sLen){
            int boundary = end[wordChar[start] - 'a'];
            while (count < boundary){
                count++;
                boundary = Math.max(boundary, end[wordChar[count] - 'a']);
            }
            int curRes = count - start + 1;
            res.add(curRes);
            start = count + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        String test = "ababcbacadefegdehijhklij";
        System.out.println(new lc763().partitionLabels(test));
    }
}
