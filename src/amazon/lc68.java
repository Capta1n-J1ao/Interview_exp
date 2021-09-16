package amazon;

/*
这道题目很烦，写起来麻烦得很，所以建议先看下视频，看下几个细节：
https://www.bilibili.com/video/BV1jJ411t7zv?from=search&seid=13504124260758089513
然后再看这个题解，个人感觉写的比较清楚：
https://leetcode-cn.com/problems/text-justification/solution/java-0ms-by-15652368128/
* */

import java.util.ArrayList;
import java.util.List;

public class lc68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int index = 0,wLen = words.length;
        List<String> res = new ArrayList<>();
        while (index < wLen){
            StringBuilder sb = new StringBuilder();
            int curRowLen = words[index].length();
//            这个变量相当于一个缓存，用来判断下一行第一个单词的位置
//            方便下面对每行单词进行分配
            int nextRowFirst = index + 1;
            while (nextRowFirst < wLen){
                if(curRowLen + 1 + words[nextRowFirst].length() > maxWidth) break;
                curRowLen += words[nextRowFirst].length() + 1;
                nextRowFirst++;
            }
//            这个是用来看除了最后一行，需要有多少个间隔
            int gaps = nextRowFirst - index - 1;
            int restLen = maxWidth - curRowLen;
            int everyGapBlank = gaps == 0 ? 0 : restLen / gaps;
            /*
            本体最难点：
            仔细考虑，如果有6个空格需要填补进4个间隙，那么应该是2，2，1，1个空格分配，
            所以我们用restLen对gaps取余，且记余数为mod，前mod个间隙，
            应该在everyGapBlank个空格基础上再多加1个空格，
            后面的间隙就不多加那1个空格了，这样便是把mod个空格均匀分配到了前几个间隙中。
            * */
            int extraBlank = gaps == 0 ? 0 : restLen % gaps;
//            两种情况
//            1. 最后一行
//            2. 非最后一行但是只有一个单词
            if(nextRowFirst == wLen || gaps == 0){
                for(int i = index; i < nextRowFirst; i++){
                    sb.append(words[i]).append(' ');
                }
                sb.deleteCharAt(sb.length() - 1);
                while (sb.length() < maxWidth) sb.append(" ");
            }else {
                for(int i = index; i < nextRowFirst - 1; i++){
                    sb.append(words[i]).append(' ');
                    for(int k = 0; k < everyGapBlank; k++) sb.append(" ");
//                    感觉整道题目最难得地方在这里，就是把不能平均分配的空格按照先后分配出去
                    if(i - index < extraBlank) sb.append(" ");
                }
                sb.append(words[nextRowFirst - 1]);
            }
            res.add(sb.toString());
            index = nextRowFirst;
        }
        return res;
    }

    public static void main(String[] args) {
        String[] test = {"This", "is", "an", "example", "of", "text", "justification"};
        System.out.println(new lc68().fullJustify(test, 16));
    }
}
