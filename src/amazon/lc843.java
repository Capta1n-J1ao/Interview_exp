package amazon;

/*
提示点：
这道题目有一个tricky的点那就是findSecretWord这个函数要自己进行十次运算

思路：
随机选一个单词，算出它和秘密单词的匹配度，
那么秘密单词一定在匹配度相同的那些单词里！
把匹配度相同的单词们选出来，再随机猜一个，算出匹配度。
这样不断缩小范围，直到找出秘密单词！

这个参考题解由于选的是随机数，性能可能不稳定。：
https://leetcode-cn.com/problems/guess-the-word/solution/chao-qing-xi-si-lu-pai-chu-fa-suo-xiao-c-5e0t/

* */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class lc843 {
//    private String[] wordlist;
//    private int wLen;
//    private List<String> res;
//    public void findSecretWord(String[] wordlist, Master master) {
//        this.wordlist = wordlist;
//        int wLen = wordlist.length;
//        if(wordlist == null || wLen == 0) return;
//        res = new ArrayList<>();
//        for(String str : wordlist) res.add(str);
//        for(int i = 0; i < 10; i++){
////            这里random的写法一定要在后面加一个括号，否则就是错的。
////            下面这种写法index就一直为0，不会动的
////            int index = (int) Math.random() * res.size();
//            int index = (int) (Math.random() * res.size());
////            记住这里一定要用res，而不能用wordlist就是因为res是不停在更新的
//            String curStr = res.get(index);
//            int distance = master.guess(curStr);
//            List<String> curRes = new ArrayList<>();
//            for(String str : res){
//                if(sameCharNum(str, curStr) == distance) curRes.add(str);
//            }
//            res = curRes;
//        }
//    }
//
//    private int sameCharNum(String strToCompare, String ref){
//        int res = 0;
//        for(int i = 0; i < strToCompare.length(); i++){
//            if(strToCompare.charAt(i) == ref.charAt(i)) res++;
//        }
//        return res;
//    }
//
//
//    和二刷题目共用
    interface Master {
        public int guess(String word);
    }

    class MyMaster implements Master {

        String secret;
        MyMaster(String secret){
            this.secret = secret;
        }
        @Override
        public int guess(String word) {
            int ans = 0;
            for (int k = 0; k < 6; k++) {
                if (secret.charAt(k) == word.charAt(k)) {
                    ans++;
                }
            }
            if (ans == 6) {
                System.out.println("找到了：" + secret);
            }
            return ans;
        }
    }

//    谷歌的面试题，二刷2021/6/8
//    而且这个代码就是有时能AC，有时候不行，因为是完全随机数
    private String[] wordlist;
    private List<String> res = new ArrayList<>();
    private int wLen;
    public void findSecretWord(String[] wordlist, Master master) {
        if(wordlist == null || wordlist.length == 0) return;
        this.res = new ArrayList<>();
        this.wordlist = wordlist;
        this.wLen = wordlist.length;
        for(String word : wordlist) res.add(word);
//        接下来就是进行十次猜测
        for(int i = 0; i < 10; i++){
            List<String> curRes = new ArrayList<>();
            Random random = new Random();
            int randomIndex = random.nextInt(res.size());
            String randomStr = res.get(randomIndex);
            int sameCharNum = master.guess(randomStr);
//            注意下面这句，很容易错
//            for(String word : wordlist){
            for(String word : res){
                if(findSameChar(word, randomStr) == sameCharNum) curRes.add(word);
            }
            res = curRes;
        }
    }

    private int findSameChar(String a, String b){
        int res = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) == b.charAt(i)) res++;
        }
        return res;
    }

    @Test
    public void test(){

        String[] wordlist = {"acckzz","ccbazz","eiowzz","abcczz"};
        findSecretWord(wordlist, new MyMaster("acckzz"));
    }
}
